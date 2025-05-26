package com.amazon.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;

	public void sendMail(String subject, String desc, String fromemail, String Toemail) {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setSubject(subject);
		message.setFrom(fromemail);
		message.setTo(Toemail);
		message.setText(desc);

		javaMailSender.send(message);

	}

}
