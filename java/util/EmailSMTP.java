/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Asus
 */
public class EmailSMTP {
    
    public EmailSMTP(String recepient, String name) throws AddressException, MessagingException, IOException{
        System.out.println("util.EmailSMTP.<init>()"+recepient);
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.ssl.enable", "false");
        prop.put("mail.smtp.tls.enable", "true");
        prop.put("mail.smtp.h public EmailSMTP(String recepient, String name) throws AddressException, MessagingException, IOException{\n" +
"    \n" +
"        Properties prop = new Properties();\n" +
"        prop.put(\"mail.smtp.auth\", true);\n" +
"        prop.put(\"mail.smtp.ssl.enable\", \"false\");\n" +
 "        prop.put(\"mail.smtp.tlsost", "smtp.mailtrap.io");
          prop.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
        prop.put("mail.smtp.port", "2525");
        prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");
      
        
        
        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("2535a510d9849c", "4e77d1078c5465");

            }

        });
    
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("tapukepapa1508@gmail.com"));
        message.setRecipients(
          Message.RecipientType.TO, InternetAddress.parse(recepient));
        message.setSubject("Pay Slip: "+name);

        String msg = "&nbsp;Your requested payslip is attached to this email."
                +"<br>"
                + " <br>&nbsp;Thanks and Regards,"
                + "<br>&nbsp;Team 75"
                +"<br>&nbsp;email: tapukepapa1508@gmail.com";

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        
        // attaching the pdf
        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        attachmentBodyPart.attachFile(new File(name+".pdf"));
        multipart.addBodyPart(attachmentBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    
    }
    
}
