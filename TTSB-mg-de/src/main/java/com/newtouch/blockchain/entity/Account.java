package com.newtouch.blockchain.entity;

import com.newtouch.blockchain.mybatis.BaseEntity;
import java.io.Serializable;
import lombok.ToString;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table SYS_ACCOUNT
 */
@ToString(callSuper=true, doNotUseGetters=true)
public class Account extends BaseEntity implements Serializable {
    /**
     * Database Column Remarks:
     *   用户名
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_ACCOUNT.USERNAME
     *
     * @mbg.generated
     */
    private String username;

    /**
     * Database Column Remarks:
     *   密码
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_ACCOUNT.PASSWORD
     *
     * @mbg.generated
     */
    private String password;
    
    private String avatar;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table SYS_ACCOUNT
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * Database Column Remarks:
     *   用户名
     *
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_ACCOUNT.USERNAME
     *
     * @return the value of SYS_ACCOUNT.USERNAME
     *
     * @mbg.generated
     */
    public String getUsername() {
        return username;
    }

    /**
     * Database Column Remarks:
     *   用户名
     *
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_ACCOUNT.USERNAME
     *
     * @param username the value for SYS_ACCOUNT.USERNAME
     *
     * @mbg.generated
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * Database Column Remarks:
     *   密码
     *
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_ACCOUNT.PASSWORD
     *
     * @return the value of SYS_ACCOUNT.PASSWORD
     *
     * @mbg.generated
     */
    public String getPassword() {
        return password;
    }

    /**
     * Database Column Remarks:
     *   密码
     *
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_ACCOUNT.PASSWORD
     *
     * @param password the value for SYS_ACCOUNT.PASSWORD
     *
     * @mbg.generated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}