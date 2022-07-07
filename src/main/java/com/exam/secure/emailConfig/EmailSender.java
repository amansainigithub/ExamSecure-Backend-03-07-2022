package com.exam.secure.emailConfig;

import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Component
public class EmailSender {

    public static boolean sendEmail(String email ,String content) throws MessagingException, IOException {
        String host="smtp.gmail.com";
        Properties properties = System.getProperties();
        System.out.println("PROPERTIES "+properties);
        //setting important information to properties object
        //host set
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");
        properties.put("authentication", "plain");
        properties.put("domain", "gmail.com");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ishumessi2@gmail.com", "stiyzsvxyrlzbyhc");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("ishumessi2@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.trim()));
        msg.setSubject("websiteName.com");

        // msg.setContent(htmlText, "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(content, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
//        MimeBodyPart attachPart = new MimeBodyPart();
//
//        attachPart.attachFile("/var/tmp/image19.png");
//        multipart.addBodyPart(attachPart);
        msg.setContent(multipart);
        Transport.send(msg);
        return true;
    }


}