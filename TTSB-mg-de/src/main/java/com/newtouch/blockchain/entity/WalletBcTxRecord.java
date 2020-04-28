package com.newtouch.blockchain.entity;

import com.newtouch.blockchain.mybatis.BaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.ToString;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table TBL_WALLET_BC_TX_RECORD
 */
@ToString(callSuper=true, doNotUseGetters=true)
public class WalletBcTxRecord extends BaseEntity implements Serializable {
    /**
     * Database Column Remarks:
     *   转出地址
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_WALLET_BC_TX_RECORD.FROM_ADDRESS
     *
     * @mbg.generated
     */
    private String fromAddress;

    /**
     * Database Column Remarks:
     *   转入地址
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_WALLET_BC_TX_RECORD.TO_ADDRESS
     *
     * @mbg.generated
     */
    private String toAddress;

    /**
     * Database Column Remarks:
     *   金额
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_WALLET_BC_TX_RECORD.AMOUNT
     *
     * @mbg.generated
     */
    private BigDecimal amount;

    /**
     * Database Column Remarks:
     *   交易时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_WALLET_BC_TX_RECORD.TRAN_TIME
     *
     * @mbg.generated
     */
    private LocalDateTime tranTime;

    /**
     * Database Column Remarks:
     *   状态
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_WALLET_BC_TX_RECORD.STATUS
     *
     * @mbg.generated
     */
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_WALLET_BC_TX_RECORD.HASH
     *
     * @mbg.generated
     */
    private String hash;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_WALLET_BC_TX_RECORD.BLOCK_NUMBER
     *
     * @mbg.generated
     */
    private Long blockNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_WALLET_BC_TX_RECORD.NONCE
     *
     * @mbg.generated
     */
    private Long nonce;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_WALLET_BC_TX_RECORD.GASPRICE
     *
     * @mbg.generated
     */
    private BigDecimal gasprice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TBL_WALLET_BC_TX_RECORD
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * Database Column Remarks:
     *   转出地址
     *
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_WALLET_BC_TX_RECORD.FROM_ADDRESS
     *
     * @return the value of TBL_WALLET_BC_TX_RECORD.FROM_ADDRESS
     *
     * @mbg.generated
     */
    public String getFromAddress() {
        return fromAddress;
    }

    /**
     * Database Column Remarks:
     *   转出地址
     *
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_WALLET_BC_TX_RECORD.FROM_ADDRESS
     *
     * @param fromAddress the value for TBL_WALLET_BC_TX_RECORD.FROM_ADDRESS
     *
     * @mbg.generated
     */
    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress == null ? null : fromAddress.trim();
    }

    /**
     * Database Column Remarks:
     *   转入地址
     *
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_WALLET_BC_TX_RECORD.TO_ADDRESS
     *
     * @return the value of TBL_WALLET_BC_TX_RECORD.TO_ADDRESS
     *
     * @mbg.generated
     */
    public String getToAddress() {
        return toAddress;
    }

    /**
     * Database Column Remarks:
     *   转入地址
     *
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_WALLET_BC_TX_RECORD.TO_ADDRESS
     *
     * @param toAddress the value for TBL_WALLET_BC_TX_RECORD.TO_ADDRESS
     *
     * @mbg.generated
     */
    public void setToAddress(String toAddress) {
        this.toAddress = toAddress == null ? null : toAddress.trim();
    }

    /**
     * Database Column Remarks:
     *   金额
     *
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_WALLET_BC_TX_RECORD.AMOUNT
     *
     * @return the value of TBL_WALLET_BC_TX_RECORD.AMOUNT
     *
     * @mbg.generated
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Database Column Remarks:
     *   金额
     *
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_WALLET_BC_TX_RECORD.AMOUNT
     *
     * @param amount the value for TBL_WALLET_BC_TX_RECORD.AMOUNT
     *
     * @mbg.generated
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Database Column Remarks:
     *   交易时间
     *
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_WALLET_BC_TX_RECORD.TRAN_TIME
     *
     * @return the value of TBL_WALLET_BC_TX_RECORD.TRAN_TIME
     *
     * @mbg.generated
     */
    public LocalDateTime getTranTime() {
        return tranTime;
    }

    /**
     * Database Column Remarks:
     *   交易时间
     *
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_WALLET_BC_TX_RECORD.TRAN_TIME
     *
     * @param tranTime the value for TBL_WALLET_BC_TX_RECORD.TRAN_TIME
     *
     * @mbg.generated
     */
    public void setTranTime(LocalDateTime tranTime) {
        this.tranTime = tranTime;
    }

    /**
     * Database Column Remarks:
     *   状态
     *
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_WALLET_BC_TX_RECORD.STATUS
     *
     * @return the value of TBL_WALLET_BC_TX_RECORD.STATUS
     *
     * @mbg.generated
     */
    public String getStatus() {
        return status;
    }

    /**
     * Database Column Remarks:
     *   状态
     *
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_WALLET_BC_TX_RECORD.STATUS
     *
     * @param status the value for TBL_WALLET_BC_TX_RECORD.STATUS
     *
     * @mbg.generated
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     *
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_WALLET_BC_TX_RECORD.HASH
     *
     * @return the value of TBL_WALLET_BC_TX_RECORD.HASH
     *
     * @mbg.generated
     */
    public String getHash() {
        return hash;
    }

    /**
     *
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_WALLET_BC_TX_RECORD.HASH
     *
     * @param hash the value for TBL_WALLET_BC_TX_RECORD.HASH
     *
     * @mbg.generated
     */
    public void setHash(String hash) {
        this.hash = hash == null ? null : hash.trim();
    }

    /**
     *
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_WALLET_BC_TX_RECORD.BLOCK_NUMBER
     *
     * @return the value of TBL_WALLET_BC_TX_RECORD.BLOCK_NUMBER
     *
     * @mbg.generated
     */
    public Long getBlockNumber() {
        return blockNumber;
    }

    /**
     *
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_WALLET_BC_TX_RECORD.BLOCK_NUMBER
     *
     * @param blockNumber the value for TBL_WALLET_BC_TX_RECORD.BLOCK_NUMBER
     *
     * @mbg.generated
     */
    public void setBlockNumber(Long blockNumber) {
        this.blockNumber = blockNumber;
    }

    /**
     *
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_WALLET_BC_TX_RECORD.NONCE
     *
     * @return the value of TBL_WALLET_BC_TX_RECORD.NONCE
     *
     * @mbg.generated
     */
    public Long getNonce() {
        return nonce;
    }

    /**
     *
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_WALLET_BC_TX_RECORD.NONCE
     *
     * @param nonce the value for TBL_WALLET_BC_TX_RECORD.NONCE
     *
     * @mbg.generated
     */
    public void setNonce(Long nonce) {
        this.nonce = nonce;
    }

    /**
     *
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_WALLET_BC_TX_RECORD.GASPRICE
     *
     * @return the value of TBL_WALLET_BC_TX_RECORD.GASPRICE
     *
     * @mbg.generated
     */
    public BigDecimal getGasprice() {
        return gasprice;
    }

    /**
     *
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_WALLET_BC_TX_RECORD.GASPRICE
     *
     * @param gasprice the value for TBL_WALLET_BC_TX_RECORD.GASPRICE
     *
     * @mbg.generated
     */
    public void setGasprice(BigDecimal gasprice) {
        this.gasprice = gasprice;
    }
}