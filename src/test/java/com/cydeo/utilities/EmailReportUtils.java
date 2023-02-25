package com.cydeo.utilities;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class EmailReportUtils {

    public static void sendEmailWithReport(String toEmail, String filePath) throws MessagingException, IOException, IOException {
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
        // create message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("belkati2017@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("Cucumber Report");
        // attach report file
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        Multipart multipart = new MimeMultipart();
        messageBodyPart.attachFile(new File(filePath));
        messageBodyPart.setHeader("Content-Type", "application/json");
        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);
        // send message
        Transport.send(message);
    }
}
