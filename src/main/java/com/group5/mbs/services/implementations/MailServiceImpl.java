package com.group5.mbs.services.implementations;

import com.group5.mbs.services.interfaces.MailService;
import lombok.AllArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;

    @Override
    @Async
    public void sendMail(final String to, final String subject, final String text) {
        final SimpleMailMessage mail = new SimpleMailMessage();

        try {
            mail.setFrom("ceng316group5mbs@gmail.com");
            mail.setTo(to);
            mail.setSubject(subject);
            mail.setText(text);
        } catch (MailException exception) {
            exception.printStackTrace();
        }

        javaMailSender.send(mail);
    }

}
