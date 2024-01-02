package com.example.demo.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
@Service
public class EmailService {
	
	

	public Map<String, String> sendMail(String email) {
		System.out.println("Outlook Email Start");
		String smtpHostServer = "smtp.office365.com";
		final String emailID = "priya.jp0210@outlook.com";
		final String password="Welcome@123";
		String toEmail = email;

		String subject = "Speed-X Sim Card Details";

		String phoneNumber = generateRandomPhoneNumber();

		String simNumber = generateSimCardNumber();

//		String messageBody = "Congratulation on successfull Request of NewSim in Speed-X ."
//				+ "Your phone number is: " + phoneNumber + " and "+
//
//				"Your sim number is: "+simNumber;
		String greetingMessage = "Welcome, " + "" + "!";

		String activationInstructions = "<p>To activate your new SIM card, please follow these steps:</p>" +

                "<ol>" +

                "<li>Insert the SIM card into your mobile device.</li>" +

                "<li>Power on your device and follow the on-screen instructions for activation.</li>" +

                "<li>If you encounter any issues, please contact our customer support.</li>" +

                "</ol>";
		
		 String messageBody = "<!DOCTYPE html>\r\n"

                + "<html>\r\n"

                + "<head>\r\n"

//                + cssStyles

                + "</head>\r\n"

                + "<body>\r\n"

                + "  <div id=\"container\">\r\n"

                + "    <p class=\"greeting\">" + greetingMessage + "</p>\r\n"

                + activationInstructions

                + "    <p>Here are your new SIM card details:</p>\r\n"

                +"<p>Your phone number is:"  + phoneNumber + "</p>\r\n"
                
				+"<p>Your sim number is:"+ simNumber+ "</p>\r\n"

                + "</body>\r\n"

                + "</html>\r\n";
		
		Map<String, String> response = new HashMap<>();
		
	    response.put("phoneNumber", phoneNumber);
	    response.put("simNumber", simNumber);
	    System.out.println(response);
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

		sendEmail(session, emailID, subject, messageBody, toEmail);
		return response;
		
	}
	public void generateBillByEmail() throws DocumentException, FileNotFoundException {

    	Document document = new Document(PageSize.A4, 50, 50, 50, 50);

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Speed-X Recharge invoice.pdf"));

        writer.setPdfVersion(PdfWriter.VERSION_1_7);

 

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, BaseColor.DARK_GRAY);

 

        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.BLACK);

 

        Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);

 

        BaseColor accentColor = new BaseColor(0, 102, 204);

 

        document.open();

 

        Paragraph title = new Paragraph("Recharge Invoice", titleFont);

 

        title.setAlignment(Element.ALIGN_CENTER);

 

        document.add(title);

 

 

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");

        String invoiceDate = dateFormat.format(new Date());

        Paragraph invoiceDetails = new Paragraph("Invoice Date: " + invoiceDate);

 

        invoiceDetails.setAlignment(Element.ALIGN_RIGHT);

 

        document.add(invoiceDetails);

 

        Paragraph customerInfo = new Paragraph("Customer Information:", headerFont);

 

        customerInfo.add(new Chunk("\nName: John Doe\nEmail: john.doe@example.com\nPhone: +1 123-456-7890", normalFont));

 

        document.add(customerInfo);

        Paragraph rechargeInfo = new Paragraph("Recharge Details:", headerFont);

 

        rechargeInfo.add(new Chunk("\nTransaction ID: 123456789\nRecharge Amount: $50.00\nRecharge Type: Mobile\nMobile Number: +1 987-654-3210", normalFont));

 

        document.add(rechargeInfo);

      

        Paragraph total = new Paragraph("Total Amount: $50.00", headerFont);

        total.setAlignment(Element.ALIGN_RIGHT);

        document.add(total);

 

        document.close();

 

 

 

        System.out.println("recharge invoice generated successfully!");

    

		

	}

	public static void sendEmail(Session session, String fromEmail, String subject, String body, String toEmail) {

        try {

            MimeMessage msg = new MimeMessage(session);

            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");

            msg.addHeader("format", "flowed");

            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(fromEmail));

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));

            msg.setSubject(subject, "UTF-8");

            msg.setSentDate(new Date());

            msg.setContent(body, "text/html; charset=utf-8");

 

            System.out.println("Message is ready");

            Transport.send(msg);

            System.out.println("Email Sent Successfully!!");

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

 

    /**

     * @param emailAddress

     * @param userName

     * @throws DocumentException

     * @throws FileNotFoundException

     * @throws MessagingException

     */

    public String sendMail(String emailAddress, String userName) throws FileNotFoundException, DocumentException, MessagingException {

        System.out.println("Outlook Email Start");

 

        String smtpHostServer = "smtp.office365.com";

        final String emailID = "sneha152001laksh@outlook.com";

        final String password = "Welcome@123";

        String toEmail = emailAddress;

 

        generateBillByEmail();

        String pdfFilePath = "Flash Recharge invoice.pdf";

        String subject = "Hi, " + userName + " - Recharge Done Succesfully";

 

       

        String cssStyles = "<style>" +

                "body { font-family: Arial, sans-serif; background-color: #f2f2f2; }" +

                "h2 { font-size: 18px; color: #333; }" +

                "#container { background-color: #fff; border-radius: 5px; padding: 20px; margin: 20px; box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2); }" +

                ".greeting { font-size: 24px; color: #009900; }" +

                "</style>";

 

       

        String greetingMessage = "Welcome, " + userName + "!";

 

       

        String activationInstructions = "<p>To activate your new SIM card, please follow these steps:</p>" +

                "<ol>" +

                "<li>Insert the SIM card into your mobile device.</li>" +

                "<li>Power on your device and follow the on-screen instructions for activation.</li>" +

                "<li>If you encounter any issues, please contact our customer support.</li>" +

                "</ol>";

 

       

        String messageBody = "<!DOCTYPE html>\r\n"

                + "<html>\r\n"

                + "<head>\r\n"

                + cssStyles

                + "</head>\r\n"

                + "<body>\r\n"

                + "  <div id=\"container\">\r\n"

                + "    <p class=\"greeting\">" + greetingMessage + "</p>\r\n"

                + activationInstructions

                + "    <p>Here are your new SIM card details:</p>\r\n"

                

                + "</body>\r\n"

                + "</html>\r\n";

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

 

        MimeMessage msg = new MimeMessage(session);

 

     

        MimeMultipart multipart = new MimeMultipart();

 

      

        MimeBodyPart messageBodyPart = new MimeBodyPart();

        messageBodyPart.setContent(messageBody, "text/html; charset=utf-8");

        multipart.addBodyPart(messageBodyPart);

 

       

        MimeBodyPart attachmentPart = new MimeBodyPart();

        FileDataSource source = new FileDataSource(pdfFilePath);

        attachmentPart.setDataHandler(new DataHandler(source));

        attachmentPart.setFileName(pdfFilePath);

        multipart.addBodyPart(attachmentPart);

 

        

        msg.setContent(multipart);

 

      

        msg.setFrom(new InternetAddress(emailID));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));

        msg.setSubject(subject, "UTF-8");

        msg.setSentDate(new Date());

 

       

        Transport.send(msg);

        System.out.println("Email Sent Successfully!!");

    

    return "Email sent successfully";

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

//	public static void sendEmail(Session session, String fromEmail, String subject, String body, String toEmail) {
//		try {
//			MimeMessage msg = new MimeMessage(session);
//			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
//			msg.addHeader("format", "flowed");
//			msg.addHeader("Content-Transfer-Encoding", "8bit");
//			msg.setFrom(new InternetAddress(fromEmail));
//			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
//			msg.setSubject(subject, "UTF-8");
//			msg.setSentDate(new Date());
//			msg.setText(body, "UTF-8");
//
//			// Add DKIM and SPF headers here if you have configured them
//
//			System.out.println("Message is ready");
//			Transport.send(msg);
//			System.out.println("Email Sent Successfully!!");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
