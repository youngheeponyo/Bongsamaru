package com.bongsamaru.common.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MailUtil {
	
	@Value("${spring.mail.username}")	
    private String senderEmail;

    @Value("${spring.mail.password}")
    private String senderPassword;

    @Value("${spring.mail.host}")
    private String mailHost;

    @Value("${spring.mail.port}")
    private String mailPort;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String mailSmtpAuth;

    @Value("${spring.mail.properties.mail.smtp.starttls.required}")
    private String mailSmtpStarttlsRequired;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String mailSmtpStarttlsEnable;

    @Value("${spring.mail.properties.mail.smtp.socketFactory.class}")
    private String mailSmtpSocketFactoryClass;

    @Value("${spring.mail.properties.mail.smtp.socketFactory.fallback}")
    private String mailSmtpSocketFactoryFallback;
    
    
    @Value("${spring.mail.properties.mail.smtp.timeout}")
    private String mailSmtpTimeout;

	public boolean sendMail(MailVO vo) {
		
		try {
	    		
			// 이메일 설정 및 인증
			Properties props = new Properties();
			props.put("mail.smtp.auth", mailSmtpAuth);
			props.put("mail.smtp.starttls.enable", mailSmtpStarttlsEnable);
			props.put("mail.smtp.host", mailHost);
			props.put("mail.smtp.port", mailPort);
			props.put("mail.smtp.timeout", mailSmtpTimeout);
			props.put("mail.smtp.socketFactory.class", mailSmtpSocketFactoryClass);
			props.put("mail.smtp.socketFactory.fallback", mailSmtpSocketFactoryFallback);
			
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(senderEmail, senderPassword);
				}
			});
			// 이메일 작성
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(vo.getRecipientEmail()));
			message.setSubject("행복마루에서 보내드리는 이메일입니다.");
			
			
			message.setContent(vo.getEmailContent(), "text/html; charset=utf-8");
			
			// 이메일 전송
			Transport.send(message);
			
		} catch (Exception e) {
			System.err.print("이메일 전송 중 에러 발생: {}");
			return false;
		}
		return true;
	}
}
