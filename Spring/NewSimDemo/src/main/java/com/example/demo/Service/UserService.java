package com.example.demo.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.User;
import com.example.demo.Repository.SimCardRepo;
import com.example.demo.Repository.UserRepository;

@Service
public class UserService{
	@Autowired
    private UserRepository userRepository;
 
    @Autowired
    private SimCardRepo simcardRepo;
    
//    @Autowired
//    private RechargePaymentRepo rechargeRepo;
    
    public User createUser(User user) {
        // Implement user registration logic here
        return userRepository.save(user);
    }
    
    
    
    public String generateRandomPhoneNumber() {
	    Random random = new Random();
	    StringBuilder phoneNumber = new StringBuilder();
	    phoneNumber.append(random.nextInt(2) + 8); // Start with 8 or 9
 
	    // Generate the remaining 9 digits
	    for (int i = 0; i < 9; i++) {
	        int digit = random.nextInt(10); // Generate a random digit between 0 and 9
	        phoneNumber.append(digit);
	    }
 
	    return phoneNumber.toString();
	}
    public static String generateSimCardNumber() {
        // Define the length of the SIM card number you want to generate
        int simCardNumberLength = 12; // Adjust the length as needed
 
        // Characters allowed in the SIM card number
        String characters = "0123456789";
        
        // Create a StringBuilder to store the generated SIM card number
        StringBuilder simCardNumber = new StringBuilder();
 
        // Create an instance of Random
        Random random = new Random();
 
        // Generate the SIM card number
        for (int i = 0; i < simCardNumberLength; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            simCardNumber.append(randomChar);
        }
        System.out.println(simCardNumber);
        return simCardNumber.toString();
    }
 
    public Map<String, String> sendMail(String emailAddress,String Phonenumber , String Simnumber ,String location) {
	    System.out.println("Outlook Email Start");
	    String smtpHostServer = "smtp.office365.com";
	    final String emailID = "priya.jp0210@outlook.com";
	    final String password = "Welcome@123";
	    String toEmail = emailAddress;
	    
	    System.out.println("Generated Phone Number from send mail: " + Phonenumber);
	    System.out.println("Generated SIM Number from send mail: " + Simnumber);
	    
 
	    String subject = "Your Plan Pro SIM Card Information";
	    String activationLink = "http://localhost:4200/starter-recharge";
	    System.out.println("Activation Link " + activationLink);
 
	    // Compose the email body
	    String messageBody = "<html>" +
	            "<head>" +
	            "<style>" +
	            "  h1 { color: #FF0000; }" +
	            "  p { font-size: 16px; }" +
	            "  a { color: #3498db; text-decoration: none; }" +
	            "  a:hover { text-decoration: underline; }" +
	            "</style>" +
	            "</head>" +
	            "<body>" +
	            "<h1>Welcome to Plan Pro!</h1>" +
	            "<p>Thank you for choosing Plan Pro for your mobile needs. Your SIM card information is below:</p>" +
	            "<p><strong>SIM Number:</strong> " + Simnumber + "</p>" +
	            "<p><strong>Phone Number:</strong> " + Phonenumber + "</p>" +
	            "<p><strong>Location Delivered:</strong> " + location + "</p>" +
	            "<p>To activate your account, please click on the following link:</p>" +
	            "<a href='" + activationLink + "'>" + activationLink + "</a>" +
	            "<p>We're excited to have you on board. Enjoy the world of Plan Pro!</p>" +
	            "</body>" +
	            "</html>";
 
	    Map<String, String> response = new HashMap<>();
	    response.put("phoneNumber", Phonenumber);
	    response.put("simNumber", Simnumber);
	   
 
	    Properties props = new Properties();
	    props.put("mail.smtp.host", smtpHostServer);
	    props.put("mail.smtp.port", "587");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    Session session = Session.getInstance(props, new Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(emailID, password);
	        }
	    });
 
	    // Create the MimeMessage
	    MimeMessage msg = new MimeMessage(session);
	    try {
			msg.setFrom(new InternetAddress(emailID));
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			msg.setSubject(subject, "UTF-8");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
	    // Set the content type to HTML
	    try {
			msg.setContent(messageBody, "text/html; charset=utf-8");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
	    // Send the email
	    try {
			Transport.send(msg);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
	    return response;
	}

}
