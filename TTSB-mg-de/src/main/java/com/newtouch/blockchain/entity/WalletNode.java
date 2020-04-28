package com.newtouch.blockchain.entity;

import com.newtouch.blockchain.mybatis.BaseEntity;
import java.io.Serializable;
import lombok.ToString;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table TBL_WALLET_NODE
 */
@ToString(callSuper=true, doNotUseGetters=true)
public class WalletNode extends BaseEntity implements Serializable {
	
	//以太坊公链
	public static final String TYPE_ETH_PUBLIC_CHAIN = "1";
	//以太坊私链
	public static final String TYPE_ETH_PRIVATE_CHAIN = "2";
	//其他
	public static final String TYPE_ETH_PRIVATE_OTHER = "3";
	
    /**
     * Database Column Remarks:
     *   链URL
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_WALLET_NODE.URL
     *
     * @mbg.generated
     */
    private String url;

    /**
     * Database Column Remarks:
     *   节点名称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_WALLET_NODE.NAME
     *
     * @mbg.generated
     */
    private String name;

    /**
     * Database Column Remarks:
     *   类型,1-以太坊公链,2-以太坊私链,3-其他
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBL_WALLET_NODE.TYPE
     *
     * @mbg.generated
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TBL_WALLET_NODE
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * Database Column Remarks:
     *   链URL
     *
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_WALLET_NODE.URL
     *
     * @return the value of TBL_WALLET_NODE.URL
     *
     * @mbg.generated
     */
    public String getUrl() {
        return url;
    }

    /**
     * Database Column Remarks:
     *   链URL
     *
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_WALLET_NODE.URL
     *
     * @param url the value for TBL_WALLET_NODE.URL
     *
     * @mbg.generated
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * Database Column Remarks:
     *   节点名称
     *
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_WALLET_NODE.NAME
     *
     * @return the value of TBL_WALLET_NODE.NAME
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * Database Column Remarks:
     *   节点名称
     *
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_WALLET_NODE.NAME
     *
     * @param name the value for TBL_WALLET_NODE.NAME
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * Database Column Remarks:
     *   类型,1-以太坊公链,2-以太坊私链,3-其他
     *
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TBL_WALLET_NODE.TYPE
     *
     * @return the value of TBL_WALLET_NODE.TYPE
     *
     * @mbg.generated
     */
    public String getType() {
        return type;
    }

    /**
     * Database Column Remarks:
     *   类型,1-以太坊公链,2-以太坊私链,3-其他
     *
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TBL_WALLET_NODE.TYPE
     *
     * @param type the value for TBL_WALLET_NODE.TYPE
     *
     * @mbg.generated
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}