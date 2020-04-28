package com.newtouch.blockchain.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.blockchain.email.EmailConfig;
import com.newtouch.blockchain.email.EmailSendInfo;
import com.newtouch.blockchain.entity.EmailRecord;

import cn.kklazy.mvc.support.CommonRequestAttributes;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailService {
	@Autowired
	private EmailSendInfo emailSendInfo;
	@Autowired
	private EmailConfig emailConfig;
	@Autowired
	private EmailRecordService emailRecordService;
	
	public void sendRegisterEmail(String toEMailAddress) throws Exception {
		String subject = "注册钱包";
		String uuid = UUID.randomUUID().toString();

		String registerConfirmHref = getAddress() + "/account/email/activation?token=" + uuid;
		String content = buildRegistEmailContent("register.html", registerConfirmHref);
		emailSendInfo.setContent(content);
		emailSendInfo.setSubject(subject);
		emailSendInfo.setToAddress(new String[] { toEMailAddress });
		emailConfig.mailSender(emailSendInfo);

		// 保存邮件记录
		EmailRecord emailRecord = new EmailRecord();
		emailRecord.setId(uuid);
		emailRecord.setEmail(toEMailAddress);
		emailRecord.setType(EmailRecord.TYPE_REGISTER);
		emailRecord.setStatus(EmailRecord.CODE_UNUSED);
		
		LocalDateTime now = LocalDateTime.now();
		// 链接有效为2小时
		emailRecord.setOutdate(now.plusHours(2));
		emailRecordService.save(emailRecord);
	}
	private String getAddress() {
		CommonRequestAttributes attributes = new CommonRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		return request.getScheme()+"://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}
	private String buildRegistEmailContent(String templateName, String url) throws IOException {
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("emailTemplate/" + templateName);
		BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		try {
			while ((line = fileReader.readLine()) != null) {
				buffer.append(line);
			}
		} catch (Exception e) {
			log.error("读取文件失败，fileName:{}", templateName, e);
		} finally {
			inputStream.close();
			fileReader.close();
		}
		strReplace(buffer, "[url1]", url);
		return buffer.toString();
	}
	private void strReplace(StringBuffer str, String replace, String replaced) {
		str.replace(str.indexOf(replace), str.indexOf(replace) + replace.length(), replaced);
	}
}
