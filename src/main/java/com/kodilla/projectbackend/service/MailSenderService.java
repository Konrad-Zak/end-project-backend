package com.kodilla.projectbackend.service;

import com.kodilla.projectbackend.domian.Mail;
import com.kodilla.projectbackend.observer.SendEmailProcessProblem;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailSenderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);
    private JavaMailSender javaMailSender;
    private SendEmailProcessProblem sendEmailProcessProblem;

    public void send(Mail mail) {
        try {
            SimpleMailMessage mailMessage = createMailMessage(mail);
            javaMailSender.send(mailMessage);
            LOGGER.info("Email has been sent");
        } catch (MailException e) {
            sendEmailProcessProblem.addMessage("Send email error");
            LOGGER.error("Failed to process email sending: " + e.getMessage());
        }
    }

    private SimpleMailMessage createMailMessage(Mail mail){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getReceiverEmail());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        return mailMessage;
    }

}
