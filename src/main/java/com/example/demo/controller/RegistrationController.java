package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.OtpService;

@Controller
public class RegistrationController {
	@Autowired
    private OtpService otpService;

	 @Autowired
	    private JavaMailSender mailSender;

	    // Send OTP
	    @PostMapping("/send-otp")
	    @ResponseBody
	    public String sendOtp(@RequestParam String email) {
	        String otp = otpService.generateOtp(email);

	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(email);
	        message.setSubject("Your Registration OTP");
	        message.setText("Your OTP is: " + otp);
	        mailSender.send(message);

	        return "OTP Sent";
	    }

	    // Verify OTP
	    @PostMapping("/verify-otp")
	    @ResponseBody
	    public boolean verifyOtp(@RequestParam String email, @RequestParam String otp) {
	        return otpService.validateOtp(email, otp);
	    }


}
