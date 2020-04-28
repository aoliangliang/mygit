package com.newtouch.blockchain.mybatis;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;

import cn.kklazy.repository.datasource.DynamicDataSourceSwitcher;

/**
 * @Package com.newtouch.blockchain.mybatis
 * @ClassName: MybatisTransactionManager
 */
public class MybatisTransactionManager extends DataSourceTransactionManager{

	/**
	*/ 
	private static final long serialVersionUID = 4393832113197298140L;
	
	public final static String TRANSACTION_MANAGER = "mybatisTransactionManager";
	
	
	@Override
	protected void doBegin(Object transaction, TransactionDefinition definition) {
		if (!definition.isReadOnly()) {
			logger.info("write, transaction;");
			DynamicDataSourceSwitcher.setMasterDataSource();
		} else {
//			logger.info("read");
			DynamicDataSourceSwitcher.setSlaveDataSource();
		}
		super.doBegin(transaction, definition);
	}

	@Override
	protected void doCommit(DefaultTransactionStatus status) {
		super.doCommit(status);
//		logger.info("transaction: commit");
	}

}
