package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	  @Autowired
	    private JavaMailSender mailSender;
	 @Value("${spring.mail.username}")  // get sender email from application.properties
	    private String senderEmail;

	 public void sendEmail(String to, String subject, String body) {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(to);              // comes from JSP (user input)
	        message.setSubject(subject);
	        message.setText(body);
	        message.setFrom(senderEmail);   // always your configured mail id

	        mailSender.send(message);
	    }
}
