package com.newtouch.blockchain.entity;

import com.newtouch.blockchain.mybatis.BaseEntity;
import java.io.Serializable;
import lombok.ToString;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table TBL_WALLET_ACCOUNT_CURRENCY
 */
@ToString(callSuper=true, doNotUseGetters=true)
public class WalletAccountCurrency extends BaseEntity implements Serializable {
	//是默认账号
	public static final String DEFAULT_ADDRESS_FLAG_YES = "1";
	//不是默认账号
	public static final String DEFAULT_ADDRESS_FLAG_NO = "0";
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_WALLET_ACCOUNT_CURRENCY.CURRENCY_ID
     *
     * @mbg.generated
     */
    private String currencyId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_WALLET_ACCOUNT_CURRENCY.ACCOUNT_ID
     *
     * @mbg.generated
     */
    private String accountId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_WALLET_ACCOUNT_CURRENCY.ADDRESS
     *
     * @mbg.generated
     */
    private String address;
    
    private String defaultAddressFlag = DEFAULT_ADDRESS_FLAG_NO;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TBL_WALLET_ACCOUNT_CURRENCY
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_WALLET_ACCOUNT_CURRENCY.CURRENCY_ID
     *
     * @return the value of TBL_WALLET_ACCOUNT_CURRENCY.CURRENCY_ID
     *
     * @mbg.generated
     */
    public String getCurrencyId() {
        return currencyId;
    }

    /**
     *
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_WALLET_ACCOUNT_CURRENCY.CURRENCY_ID
     *
     * @param currencyId the value for TBL_WALLET_ACCOUNT_CURRENCY.CURRENCY_ID
     *
     * @mbg.generated
     */
    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId == null ? null : currencyId.trim();
    }

    /**
     *
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_WALLET_ACCOUNT_CURRENCY.ACCOUNT_ID
     *
     * @return the value of TBL_WALLET_ACCOUNT_CURRENCY.ACCOUNT_ID
     *
     * @mbg.generated
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     *
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_WALLET_ACCOUNT_CURRENCY.ACCOUNT_ID
     *
     * @param accountId the value for TBL_WALLET_ACCOUNT_CURRENCY.ACCOUNT_ID
     *
     * @mbg.generated
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    /**
     *
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_WALLET_ACCOUNT_CURRENCY.ADDRESS
     *
     * @return the value of TBL_WALLET_ACCOUNT_CURRENCY.ADDRESS
     *
     * @mbg.generated
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_WALLET_ACCOUNT_CURRENCY.ADDRESS
     *
     * @param address the value for TBL_WALLET_ACCOUNT_CURRENCY.ADDRESS
     *
     * @mbg.generated
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

	public String getDefaultAddressFlag() {
		return defaultAddressFlag;
	}

	public void setDefaultAddressFlag(String defaultAddressFlag) {
		this.defaultAddressFlag = defaultAddressFlag;
	}
}