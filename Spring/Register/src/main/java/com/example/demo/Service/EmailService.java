package com.example.demo.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

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

import com.example.demo.Repo.RegisterRepo;
import com.example.demo.entity.Register;
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
	
	@Autowired
	RegisterRepo regRepo;
	
	
	public void generateBillByEmail(String emailAddress) throws DocumentException, FileNotFoundException {
		Register reg=regRepo.findByEmailId(emailAddress);

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

 

        customerInfo.add(new Chunk("\nName: "+reg.getFirstName() +"\nEmail:"+reg.getEmailId() +"\nPhone:"+reg.getPhoneNumber(), normalFont));

 

        document.add(customerInfo);

        Paragraph rechargeInfo = new Paragraph("Recharge Details:", headerFont);

 

        rechargeInfo.add(new Chunk("\nRecharge Amount:" + reg.getPlan() +"\nRecharge Type: Mobile\nMobile Number:"+reg.getPhoneNumber()+"\nPlanValidity:"+ reg.getPlanValidity(), normalFont));

 

        document.add(rechargeInfo);

      

        Paragraph total = new Paragraph("Total Amount:"+reg.getPlan(), headerFont);

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

    public String sendMail(String emailAddress) throws FileNotFoundException, DocumentException, MessagingException {

        System.out.println("Outlook Email Start");

 

        String smtpHostServer = "smtp.office365.com";

        final String emailID = "priya.jp0210@outlook.com";

        final String password = "Welcome@123";

        String toEmail = emailAddress;

 

        generateBillByEmail(emailAddress);

        String pdfFilePath = "Speed-X Recharge invoice.pdf";

        String subject = " Recharge Done Succesfully";

 

       

        String cssStyles = "<style>" +

                "body { font-family: Arial, sans-serif; background-color: #f2f2f2; }" +

                "h2 { font-size: 18px; color: #333; }" +

                "#container { background-color: #fff; border-radius: 5px; padding: 20px; margin: 20px; box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2); }" +

                ".greeting { font-size: 24px; color: #009900; }" +

                "</style>";

 

       

        String greetingMessage = "Invoice Bill, ";

 

       

        String activationInstructions = "<p> Your Speed-X Invoice Bill</p>" +

                "<ol>" +

                "<li>Recharge payment is done succussfully.</li>" +

                "<li>For more details regarding the Recharge and other information ,please refer the below given Attachment.</li>" +

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
	
	

}
