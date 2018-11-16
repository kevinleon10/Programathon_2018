package codeSpitters.programathon_2018.service.mail;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.Date;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

/**
 * @author JorgeRemon
 */
public class SendMail {

    Message message;
    Properties props;
    Session session;

    final String username = "codespitters.team@gmail.com";
    final String password = "CodeSpitters2018";

    public SendMail() {
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    }

    public boolean sendNotificationEmail(String userEmail, String responsibleName, String childName) throws IOException {

        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));

            // recipients email address
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));

            // add the Subject of email
            message.setSubject("Recordatorio - Diagnostico temprano");

            Multipart multipart = new MimeMultipart();

            // add the body message
            BodyPart bodyPart = new MimeBodyPart();

            String htmlBody = NotificationFormats.prepareDailyNotification(responsibleName, childName);

            bodyPart.setContent(htmlBody, "text/html");
            multipart.addBodyPart(bodyPart);

            message.setContent(multipart);

            Transport.send(message);

        } catch (MessagingException e) {
            return false;
        }
        return true;
    }

    public boolean sendFinalEmail(String userEmail, String responsibleName, String childName) throws IOException {

        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));

            // recipients email address
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));

            // add the Subject of email
            message.setSubject("Recordatorio - Diagnostico temprano");

            Multipart multipart = new MimeMultipart();

            // add the body message
            BodyPart bodyPart = new MimeBodyPart();

            String htmlBody = NotificationFormats.prepareFinalNotification(responsibleName, childName);

            bodyPart.setContent(htmlBody, "text/html");
            multipart.addBodyPart(bodyPart);

            message.setContent(multipart);

            Transport.send(message);

        } catch (MessagingException e) {
            return false;
        }
        return true;
    }
}
