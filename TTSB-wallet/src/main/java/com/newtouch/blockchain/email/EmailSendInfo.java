package com.newtouch.blockchain.email;

import java.util.Properties;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sun.mail.util.MailSSLSocketFactory;

import cn.kklazy.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EmailSendInfo {

	
	
	
	// 发送邮件的服务器ip和端口
	@Value("${spring.mail.host}")
	private String mailServerHost;
	
	@Value("${spring.mail.port}")
	private String mailServerPort;
	
	// 邮件发送者的地址
	@Value("${spring.mail.username}")
	private String fromAddress;
	
	// 邮件接收者的地址
	private String[] toAddress;
	
	// 邮件抄送的地址
	private String[] toCCAddress;
	
	// 邮件密送的地址
	private String[] toBCCAddress;
	
	// 登录邮件发送服务器的用户名和密码
	
	@Value("${spring.mail.username}")
	private String username;
	
	@Value("${spring.mail.password}")
	private String password;
	
	// 是否需要身份验证
	@Value("${spring.mail.validate}")
	private boolean validate;
	
	// 邮件主题
	private String subject;
	
	// 邮件的文本内容
	private String content;
	
	// 邮件附件的文件名
	private String[] attachFileNames;
	
	/**
	 * 获得邮件会话属性
	* @Title: properties  
	* @Description: TODO 
	* @param @return    参数  
	* @return Properties    返回类型  
	* @throws
	 */
	public Properties getProperties() {
	    Properties props = new Properties();       
     
     //设置用户的认证方式
        props.put("mail.smtp.auth", "true");
        //设置传输协议
        props.setProperty("mail.transport.protocol", "smtp");
       
        //设置发件人的SMTP服务器地址
        props.setProperty("mail.smtp.host", this.mailServerHost);
        if(StringUtils.isNotBlank(this.mailServerPort)) {
        	props.setProperty("mail.smtp.port", this.mailServerPort);
 	        props.setProperty("mail.smtp.socketFactory.port", this.mailServerPort); 
        }
        
     // 如果设置,指定实现javax.net.SocketFactory接口的类的名称,这个类将被用于创建SMTP的套接字
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
     // 如果设置为true,未能创建一个套接字使用指定的套接字工厂类将导致使用java.net.Socket创建的套接字类。默认值为true。
        props.setProperty("mail.smtp.socketFactory.fallback", "false");  
        
        
        return props;
	 }
	
	/**
	 * 获得邮件会话属性 SSL
	* @Title: properties  
	* @Description: TODO 
	* @param @return    参数  
	* @return Properties    返回类型  
	* @throws
	 */
	public Properties getSSLProperties() {
        System.setProperty("javax.net.ssl.trustStore", "sv8089.xserver.jp-1");
	    Properties props = System.getProperties();
     
	    //设置用户的认证方式
        props.put("mail.smtp.auth", "true");
        //设置传输协议
        props.setProperty("mail.transport.protocol", "smtp");
       
        //设置发件人的SMTP服务器地址
        props.setProperty("mail.smtp.host", this.mailServerHost);
        if(StringUtils.isNotBlank(this.mailServerPort)) {
        	props.setProperty("mail.smtp.port", this.mailServerPort);
 	        props.setProperty("mail.smtp.socketFactory.port", this.mailServerPort); 
        }
        props.setProperty("mail.user", this.username);
        props.setProperty("mail.password", this.password);
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);


        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        
        
     // 如果设置,指定实现javax.net.SocketFactory接口的类的名称,这个类将被用于创建SMTP的套接字
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
     // 如果设置为true,未能创建一个套接字使用指定的套接字工厂类将导致使用java.net.Socket创建的套接字类。默认值为true。
        props.setProperty("mail.smtp.socketFactory.fallback", "false");  
        return props;
	 }

	/**
	 * @return the mailServerHost
	 */
	public String getMailServerHost() {
		return mailServerHost;
	}

	/**
	 * @param mailServerHost the mailServerHost to set
	 */
	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	/**
	 * @return the mailServerPort
	 */
	public String getMailServerPort() {
		return mailServerPort;
	}

	/**
	 * @param mailServerPort the mailServerPort to set
	 */
	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	/**
	 * @return the fromAddress
	 */
	public String getFromAddress() {
		return fromAddress;
	}

	/**
	 * @param fromAddress the fromAddress to set
	 */
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	/**
	 * @return the toAddress
	 */

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the toAddress
	 */
	public String[] getToAddress() {
		return toAddress;
	}

	/**
	 * @param toAddress the toAddress to set
	 */
	public void setToAddress(String[] toAddress) {
		this.toAddress = toAddress;
	}

	/**
	 * @return the toCCAddress
	 */
	public String[] getToCCAddress() {
		return toCCAddress;
	}

	/**
	 * @param toCCAddress the toCCAddress to set
	 */
	public void setToCCAddress(String[] toCCAddress) {
		this.toCCAddress = toCCAddress;
	}

	/**
	 * @return the toBCCAddress
	 */
	public String[] getToBCCAddress() {
		return toBCCAddress;
	}

	/**
	 * @param toBCCAddress the toBCCAddress to set
	 */
	public void setToBCCAddress(String[] toBCCAddress) {
		this.toBCCAddress = toBCCAddress;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the validate
	 */
	public boolean isValidate() {
		return validate;
	}

	/**
	 * @param validate the validate to set
	 */
	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the attachFileNames
	 */
	public String[] getAttachFileNames() {
		return attachFileNames;
	}

	/**
	 * @param attachFileNames the attachFileNames to set
	 */
	public void setAttachFileNames(String[] attachFileNames) {
		this.attachFileNames = attachFileNames;
	}
	
	
}
