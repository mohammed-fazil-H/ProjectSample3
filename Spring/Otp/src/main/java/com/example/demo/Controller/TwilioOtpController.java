package com.example.demo.Controller;

 

import java.util.HashMap;
import java.util.Map;

 

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.EmailService;
import com.example.demo.Service.TwilioOTPService;

import lombok.extern.slf4j.Slf4j;

 

@RestController
@RequestMapping
@Slf4j
public class TwilioOtpController {
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private TwilioOTPService otpService;
	
	
	@CrossOrigin(origins="http://localhost:4200/")
	@PostMapping("/requestotp")
	public ResponseEntity<Map<String, String>> requestOTP(@RequestBody Map<String, String> requestBody) {
	    String phoneNumber = requestBody.get("phoneNumber");
	    System.out.println(phoneNumber);


	    String otp = otpService.generateRandomOTP();
	    if (otpService.sendOtp(phoneNumber, otp)) {
	    	   Map<String, String> response = new HashMap<>();
	           response.put("message", "OTP sent successfully");
	           return ResponseEntity.ok(response);
	    }
	    return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(null);

	}
//	@GetMapping("/NewSim")
//	public void requestSim(String mail) {
//		
//		new EmailService().sendMail(mail);
//		
//	}
	
	@ResponseBody

	@PostMapping("/NewSim")

	@CrossOrigin(origins="http://localhost:4200")

	public Map<String, String> sendMail(@RequestBody String email)

	{

		String emailAddress=email;

		Map<String, String> response =emailService.sendMail(emailAddress);
		System.out.println(response);

		return response;

	}
	@CrossOrigin(origins="http://localhost:4200/")

	 @PostMapping("/generateBill")

	 public ResponseEntity<String> generateBill(@RequestBody String emailAddress) {

	        try {
	        	System.out.println(emailAddress);


	        	emailService.generateBillByEmail();

	            String userName = "John Doe"; // Replace with the actual user name

	            String result = emailService.sendMail(emailAddress, userName);
	            
	            


	            return ResponseEntity.ok("Bill generated and email sent successfully: " + result);

	        } catch (Exception e) {

	            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Error generating bill and sending email");

	        }

	    }
	

	
	@CrossOrigin(origins="http://localhost:4200/")

	  @PostMapping("/validateotp")

	    public ResponseEntity<Boolean> validateOtp(@RequestBody Map<String, String> requestBody) {

	        String phoneNumber = requestBody.get("phoneNumber");

	        String userEnteredOtp = requestBody.get("otp");



	        System.out.println("Received OTP: " + userEnteredOtp);

	        System.out.println("Received Phone Number: " + phoneNumber);

	        

	        boolean isOtpValid = otpService.validateOtp(phoneNumber, userEnteredOtp);
	        return ResponseEntity.ok().body(isOtpValid);



//	        if (isOtpValid) {
//
//	            return ResponseEntity.ok("OTP is valid");
//
//	        } else {
//
//	            return ResponseEntity.badRequest().body("Invalid OTP");
//
//	        }

	    }

}