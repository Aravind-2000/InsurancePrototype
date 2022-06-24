package com.example.insuranceprototype.Email;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;


@Slf4j
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;


    public void sendEmail(String to, String subject,String body) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("lakshmi.nv@futurainstech.com");
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        mailSender.send(mailMessage);
        System.out.println("Mail Sent Successfully!!");
    }

    public void sendMail(String to, String subject, String body, String filepath){
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
                mimeMessage.setFrom(new InternetAddress("lakshmi.nv@futurainstech.com"));
                mimeMessage.setSubject(subject);
                Multipart multipart = new MimeMultipart();
                MimeBodyPart attachment = new MimeBodyPart();
                MimeBodyPart bodyText = new MimeBodyPart();
                try {
                    attachment.attachFile(filepath);
                    bodyText.setText(body);
                    multipart.addBodyPart(attachment);
                    multipart.addBodyPart(bodyText);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mimeMessage.setContent(multipart);
            }
        };
        try{
            mailSender.send(preparator);
            log.info("Mail sent succesfully");
        }
        catch (MailException e){
            log.info(e.getMessage());
        }
    }
}
