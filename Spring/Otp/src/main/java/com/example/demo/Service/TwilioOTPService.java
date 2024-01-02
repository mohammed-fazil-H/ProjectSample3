package com.example.demo.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Config.TwilioConfig;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import lombok.extern.slf4j.Slf4j;

 

 

@Service
@Slf4j
public class TwilioOTPService {
	@Autowired
	private TwilioConfig twilioConfig;
	
	private Map<String, String> otpStorage = new HashMap<>();



 

	public String generateRandomOTP() {
        Random random = new Random();
        int otpValue = 100000 + random.nextInt(900000); // Generates a number between 100000 and 999999
        return String.valueOf(otpValue);
    }

 

public boolean sendOtp(String phoneNumber, String otp) {
	try {
		Twilio.init(twilioConfig.getAccountSid(),twilioConfig.getAuthToken());
		 Message message = Message.creator(
			      new com.twilio.type.PhoneNumber("+91"+phoneNumber),
			      new com.twilio.type.PhoneNumber("+12564108981"),
			      "Your OTP is:"+otp)
			    .create();
		 otpStorage.put(phoneNumber, otp);
		 return true;
	}catch(Exception e)
	{

	e.printStackTrace();
		return false;
	}
}
public boolean validateOtp(String phoneNumber, String userEnteredOtp) {

    // Retrieve the previously generated OTP for the given phone number from the map

    String storedOtp = otpStorage.get(phoneNumber);

    

    // Compare the user-entered OTP with the stored OTP

    Boolean s= storedOtp != null && storedOtp.equals(userEnteredOtp);

    System.out.println(s);

    System.out.println(storedOtp);

    return s;

    

}



 

public String generateOtp() {
	// TODO Auto-generated method stub
	return null;
}}