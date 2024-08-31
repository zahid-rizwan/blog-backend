package com.backend.blog.services.impl;

import com.backend.blog.payloads.MailBody;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
@Service
public class EmailServices {
   private final JavaMailSender javaMailSender;
   public EmailServices(JavaMailSender javaMailSender) {
       this.javaMailSender = javaMailSender;
   }
   public void SendSimpleMessage(MailBody mailBody){
       SimpleMailMessage message = new SimpleMailMessage();
       message.setTo(mailBody.to());
       message.setFrom("zahidibnrizwan@gmail.com");
       message.setSubject(mailBody.subject());
       message.setText(mailBody.text());
       javaMailSender.send(message);

   }
}
