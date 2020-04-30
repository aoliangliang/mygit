package com.newtouch.blockchain.scheduled;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthBlock.Block;
import org.web3j.protocol.core.methods.response.EthBlock.TransactionResult;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import com.newtouch.blockchain.entity.WalletBcTxRecord;
import com.newtouch.blockchain.service.WalletBcTxRecordService;
import com.newtouch.blockchain.service.WalletEthService;

import cn.kklazy.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class WalletBcTxTask {
	@Autowired
	private WalletBcTxRecordService walletBcTxRecordService;
	@Autowired
	private WalletEthService walletEthService;
	
//	@Scheduled(cron = "*/5 * * * * ?")
	@SuppressWarnings("rawtypes")
	public void getBcTxRecord() {
		try {
			log.info("task started");
			
			BigInteger latestBlockNumber = walletEthService.getEthBlockNumber();
			if (latestBlockNumber == null) {
				log.error("获取最新区块数失败");
				return;
			}
			log.info("latestBlockNumber:{}", latestBlockNumber);

			Long maxTxBlockNumber = walletBcTxRecordService.findMaxTxBlockNumber();
			if (maxTxBlockNumber == null) {
				maxTxBlockNumber = 0L;
			}
			log.info("maxBlockNumber:{}", maxTxBlockNumber);
			// 数据库区块号和链中区块号相同
			if (maxTxBlockNumber.compareTo(latestBlockNumber.longValue()) == 0) {
				return;
			}
			for (Long i = latestBlockNumber.longValue(); i > maxTxBlockNumber; i--) {
				Block block = walletEthService.getEthBlock(BigInteger.valueOf(i));
				if (block == null) {
					log.error("获取区块数据失败");
					break;
				}
				List<EthBlock.TransactionResult> results = block.getTransactions();
				if (results.isEmpty()) {
					log.info("第"+i+"个区块没有交易数据");
					continue;
				}
				
				for(TransactionResult result : results) {
					EthBlock.TransactionObject transaction = (EthBlock.TransactionObject) result;
					if (transaction == null) {
						break;
					}
					String inputData = transaction.getInput();
					if (StringUtils.isBlank(inputData)) {
						continue;
					}
					String method = inputData.substring(0, 10);
					//"0x80318be8"对应方法名transferOverride
					if (!"0x80318be8".equals(method)) {
						continue;
					}
					String from = inputData.substring(10, 74);
			        String to = inputData.substring(74, 138);
			        String value = inputData.substring(138);
			        if (StringUtils.isBlank(from) || StringUtils.isBlank(to) || StringUtils.isBlank(value)) {
						continue;
					}
					String fromAddress = walletEthService.hexToAddress(from);
					String toAddress = walletEthService.hexToAddress(to);
					BigInteger amount = walletEthService.hexToBigInteger(value);
					
					WalletBcTxRecord bcTxRecord = new WalletBcTxRecord();
					bcTxRecord.setFromAddress(fromAddress);
					bcTxRecord.setToAddress(toAddress);
					bcTxRecord.setAmount(new BigDecimal(amount));
					bcTxRecord.setBlockNumber(block.getNumber().longValue());
					bcTxRecord.setHash(transaction.getHash());
					TransactionReceipt transactionReceipt = walletEthService.geTransactionReceipt(transaction.getHash());
					if (transactionReceipt == null) {
						continue;
					}
					String status = transactionReceipt.getStatus();
					bcTxRecord.setStatus(status.substring(2));
					bcTxRecord.setGasprice(new BigDecimal(transaction.getGasPrice()));
					bcTxRecord.setNonce(transaction.getNonce().longValue());
					
					BigInteger time = block.getTimestamp();
					Timestamp timestamp = Timestamp.from(Instant.ofEpochSecond(time.longValue()));
					bcTxRecord.setTranTime(timestamp.toLocalDateTime());
					walletBcTxRecordService.save(bcTxRecord);
		        }
			}
			log.info("task end");
		} catch (Exception e) {
			log.error("{}", e.getMessage());
		}
	}
}
