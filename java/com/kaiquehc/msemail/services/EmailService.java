package com.kaiquehc.msemail.services;

import com.kaiquehc.msemail.enums.StatusEmail;
import com.kaiquehc.msemail.models.EmailModel;
import com.kaiquehc.msemail.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    JavaMailSender emailSender;

    public EmailModel sendEmail(EmailModel emailModel) {
        emailModel.setSendDateEmail(LocalDateTime.now());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            emailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.SENT);
        } catch (MailException err) {
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return emailRepository.save(emailModel);
        }
    }
}
