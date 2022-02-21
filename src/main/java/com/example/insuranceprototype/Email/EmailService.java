package com.example.insuranceprototype.Email;


import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@Slf4j
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

/*
    public void sendEmail(String to, String subject,String body) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("lakshmi.nv@futurainstech.com");
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        mailSender.send(mailMessage);
        System.out.println("Email Sent Successfully!!");
    }
*/

    public void sendMail(String to, String subject, String body){
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
                mimeMessage.setFrom(new InternetAddress("lakshmi.nv@futurainstech.com"));
                mimeMessage.setSubject(subject);
                mimeMessage.setText(body);
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
