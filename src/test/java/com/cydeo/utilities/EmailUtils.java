package com.cydeo.utilities;



    import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

    public class EmailUtils {

        public static void sendEmailWithAttachment(String recipient, String subject, String body, String attachmentFilePath, String jsonReport) throws MessagingException {

            String sender = "belkati2017@gmail.com";
            String password = "vutbntcmloeveyvu";

            String host = "smtp.gmail.com";
            int port = 465;

            Properties properties = new Properties();
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", port);

            Session session = Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(sender, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(body, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            File attachmentFile = new File(attachmentFilePath);
            if (attachmentFile.exists()) {
                MimeBodyPart attachmentPart = new MimeBodyPart();
                DataSource source = new FileDataSource(attachmentFile);
                attachmentPart.setDataHandler(new DataHandler(source));
                attachmentPart.setFileName(attachmentFile.getName());
                multipart.addBodyPart(attachmentPart);
            }

            message.setContent(multipart);

            Transport.send(message);
        }
    }


