package com.newtouch.blockchain.email;

import java.security.Security;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.context.annotation.Configuration;

import com.newtouch.blockchain.exception.BusinessException;

@Configuration
public class EmailConfig {
	

	 
	/**
	 * 发送简单纯文本文件
	 * @param topic
	 * @param text
	 * @param senderAddress
	 * @param recipientAddress
	 * @throws Exception
	 */
	 @SuppressWarnings("restriction")
	public  void mailSender(EmailSendInfo emailSendInfo) throws Exception{
		 // 1.获取会话属性
		 Properties props = emailSendInfo.getProperties();
		 Session session = Session.getInstance(props);
		//2、创建定义整个应用程序所需的环境信息的 Session 对象
		 //如果需要身份验证，即SSL
		 if(emailSendInfo.isValidate()) {
			 Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			 Authenticator auth = new Authenticator() {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(emailSendInfo.getUsername(), emailSendInfo.getPassword());
	            }
	        };
	        props = emailSendInfo.getSSLProperties();
	        session = Session.getInstance(props, auth);
		 }
		        
        //设置调试信息在控制台打印出来
        session.setDebug(true);
        //3、创建邮件的实例对象
        Message msg = getMimeMessage(session,emailSendInfo);
        //4、根据session对象获取邮件传输对象Transport
        Transport transport = session.getTransport();
        //设置发件人的账户名和密码
		transport.connect(emailSendInfo.getFromAddress(), emailSendInfo.getPassword());
		transport.sendMessage(msg,msg.getAllRecipients());
		transport.close();
			
	}
	 
		/**
		 * 发送带附件的邮件
		 * @param topic
		 * @param text
		 * @param senderAddress
		 * @param recipientAddress
		 * @throws Exception
		 */
		 public  void mailSenderFile(EmailSendInfo emailSendInfo) throws Exception {
				 
				Properties props = emailSendInfo.getProperties();
		        //2、创建定义整个应用程序所需的环境信息的 Session 对象
		        Session session = Session.getInstance(props);
		        //设置调试信息在控制台打印出来
		        session.setDebug(true);
		        //3、创建邮件的实例对象
		        Message msg = getMimeMessageFile(session,emailSendInfo);
		        if (msg == null) {
					throw new BusinessException("创建邮件实例出错");
				}
		        //4、根据session对象获取邮件传输对象Transport
		        Transport transport = session.getTransport();
		        //设置发件人的账户名和密码
		        transport.connect(emailSendInfo.getFromAddress(), emailSendInfo.getPassword());
				transport.sendMessage(msg,msg.getAllRecipients());
				transport.close();
		}
	 

	 
	 public static MimeMessage getMimeMessage(Session session, EmailSendInfo emailSendInfo) throws Exception{
	        //创建一封邮件的实例对象
	        MimeMessage msg = new MimeMessage(session);
	        //设置发件人地址
	        msg.setFrom(new InternetAddress(emailSendInfo.getFromAddress()));
	        /**
	         * 设置收件人地址（可以增加多个收件人、抄送、密送），即下面这一行代码书写多行
	         * MimeMessage.RecipientType.TO:发送
	         * MimeMessage.RecipientType.CC：抄送
	         * MimeMessage.RecipientType.BCC：密送
	         */
	        
	        if(emailSendInfo.getToAddress().length <= 0) {
	        	return null;
	        }
	        
	        InternetAddress[] toAddresses = new InternetAddress[emailSendInfo.getToAddress().length];
			for(int i=0; i< emailSendInfo.getToAddress().length;i++) {
				toAddresses[i] = new InternetAddress(emailSendInfo.getToAddress()[i]);
			}
			// 创建邮件的接收者地址，并设置到邮件消息中 
//			Address to = new InternetAddress(emailSendInfo.getToAddress());
			msg.setRecipients(Message.RecipientType.TO, toAddresses);
			
			// 设置抄送地址
			if(emailSendInfo.getToCCAddress() != null && emailSendInfo.getToCCAddress().length > 0) {
				InternetAddress[] toCCAddresses = new InternetAddress[emailSendInfo.getToCCAddress().length];
				for(int i=0; i< emailSendInfo.getToCCAddress().length;i++) {
					toCCAddresses[i] = new InternetAddress(emailSendInfo.getToCCAddress()[i]);
				}
				msg.setRecipients(Message.RecipientType.CC, toAddresses);
			}
			
			// 设置密送地址
			if(emailSendInfo.getToBCCAddress() != null && emailSendInfo.getToBCCAddress().length > 0) {
				InternetAddress[] toBCCAddresses = new InternetAddress[emailSendInfo.getToBCCAddress().length];
				for(int i=0; i< emailSendInfo.getToBCCAddress().length;i++) {
					toBCCAddresses[i] = new InternetAddress(emailSendInfo.getToBCCAddress()[i]);
				}
				msg.setRecipients(Message.RecipientType.BCC, toBCCAddresses);
			}
	        
	        //设置邮件主题
	        msg.setSubject(emailSendInfo.getSubject(),"UTF-8");
	        //设置邮件正文
	        msg.setContent(emailSendInfo.getContent(), "text/html;charset=UTF-8");
	        //设置邮件的发送时间,默认立即发送
	        msg.setSentDate(new Date());
	         
	        return msg;
	    }
	 
	 public static MimeMessage getMimeMessageFile(Session session, EmailSendInfo emailSendInfo) throws Exception{
	        //创建一封邮件的实例对象
	        MimeMessage msg = new MimeMessage(session);
	        //设置发件人地址
	        msg.setFrom(new InternetAddress(emailSendInfo.getFromAddress()));
	        /**
	         * 设置收件人地址（可以增加多个收件人、抄送、密送），即下面这一行代码书写多行
	         * MimeMessage.RecipientType.TO:发送
	         * MimeMessage.RecipientType.CC：抄送
	         * MimeMessage.RecipientType.BCC：密送
	         */
	        if(emailSendInfo.getToAddress().length <= 0) {
	        	return null;
	        }
	        
	        InternetAddress[] toAddresses = new InternetAddress[emailSendInfo.getToAddress().length];
			for(int i=0; i< emailSendInfo.getToAddress().length;i++) {
				toAddresses[i] = new InternetAddress(emailSendInfo.getToAddress()[i]);
			}
			// 创建邮件的接收者地址，并设置到邮件消息中 
//			Address to = new InternetAddress(emailSendInfo.getToAddress());
			msg.setRecipients(Message.RecipientType.TO, toAddresses);
			
			// 设置抄送地址
			if(emailSendInfo.getToCCAddress().length > 0) {
				InternetAddress[] toCCAddresses = new InternetAddress[emailSendInfo.getToCCAddress().length];
				for(int i=0; i< emailSendInfo.getToCCAddress().length;i++) {
					toCCAddresses[i] = new InternetAddress(emailSendInfo.getToCCAddress()[i]);
				}
				msg.setRecipients(Message.RecipientType.CC, toAddresses);
			}
			
			// 设置密送地址
			if(emailSendInfo.getToBCCAddress().length > 0) {
				InternetAddress[] toBCCAddresses = new InternetAddress[emailSendInfo.getToBCCAddress().length];
				for(int i=0; i< emailSendInfo.getToBCCAddress().length;i++) {
					toBCCAddresses[i] = new InternetAddress(emailSendInfo.getToBCCAddress()[i]);
				}
				msg.setRecipients(Message.RecipientType.BCC, toBCCAddresses);
			}
	        
	        //设置邮件主题
	        msg.setSubject(emailSendInfo.getSubject(),"UTF-8");

	     
	        Multipart mainPart = new MimeMultipart();    
	        // 创建一个包含HTML内容的MimeBodyPart    
	        BodyPart bodyPart = new MimeBodyPart();    
	        //设置TEXT内容
//	            bodyPart.setText(mailInfo.getContent());
	        // 设置HTML内容    
	        bodyPart.setContent(emailSendInfo.getContent(), "text/html; charset=utf-8");    
	        mainPart.addBodyPart(bodyPart);    
	        //设置附件
	         String[] filenames = emailSendInfo.getAttachFileNames();
	         for (int i = 0; i < filenames.length; i++) {
	             // Part two is attachment  
	             bodyPart = new MimeBodyPart();  
	             String filename = filenames[i];//1.txt/sohu_mail.jpg
	             FileDataSource source = new FileDataSource(filename); 
	             bodyPart.setDataHandler(new DataHandler(source));  
	             bodyPart.setFileName(source.getName());  
	             mainPart.addBodyPart(bodyPart);  
	        }
	        // 将MiniMultipart对象设置为邮件内容    
	         msg.setContent(mainPart); 
	      
	        
	        //设置邮件的发送时间,默认立即发送
	        msg.setSentDate(new Date());
	         
	        return msg;
	    }
	 
//	 private Properties getProperties() {
//		 Properties props = new Properties();
//	        //设置用户的认证方式
//	        props.setProperty("mail.smtp.auth", "true");
//	        //设置传输协议
//	        props.setProperty("mail.transport.protocol", "smtp");
//	        //设置发件人的SMTP服务器地址
//	        props.setProperty("mail.smtp.host", emailIp());
//
//	     // 如果设置,指定实现javax.net.SocketFactory接口的类的名称,这个类将被用于创建SMTP的套接字
//	        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); 
//	     // 如果设置为true,未能创建一个套接字使用指定的套接字工厂类将导致使用java.net.Socket创建的套接字类。默认值为true。
//	        props.setProperty("mail.smtp.socketFactory.fallback", "false");  
//	        if(StringUtils.isNotBlank(emailPort())) {
//	        	props.setProperty("mail.smtp.port", emailPort());
//	 	        props.setProperty("mail.smtp.socketFactory.port", emailPort()); 
//	        }
//	       
//	        return props;
//	 }
	 
	 
}
