package com.cydeo.utilities;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailReportUti {

    public static void sendEmailReport(String toEmail, String filePath) throws MessagingException, IOException, IOException{
        // create mail session
        Properties props = new Properties();
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "456");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("belkati2017@gmail.com", "vutbntcmloeveyvu");
            }
        });

        // Create a message object
        Message message = new MimeMessage(session);

        // Set the sender and recipient addresses
        message.setFrom(new InternetAddress("elkati2017@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));

        // Set the subject and body of the email
        message.setSubject("Cucumber Report");
        message.setText("Please find attached the Cucumber report.");

        // Create a MimeMultipart object to represent the message body and attachment
        MimeMultipart multipart = new MimeMultipart();

        // Create a MimeBodyPart object to represent the attachment
        MimeBodyPart attachmentBodyPart = new MimeBodyPart();

        // Set the data source for the attachment to the Cucumber report file
        DataSource source = new FileDataSource(new File(filePath));
        attachmentBodyPart.setDataHandler(new DataHandler(source));
        attachmentBodyPart.setFileName("cucumber-report.html");

        // Add the attachment to the MimeMultipart object
        multipart.addBodyPart(attachmentBodyPart);

// Set the message body to the MimeMultipart object
        message.setContent(multipart);

// Send the email
        Transport.send(message);

        System.out.println("Email sent successfully.");


    }
}
