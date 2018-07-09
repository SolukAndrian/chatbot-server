package com.epam.chatbotserver.service.impl;

import com.epam.chatbotserver.models.User;
import com.epam.chatbotserver.repository.UserRepository;
import com.epam.chatbotserver.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.nio.charset.Charset;
import java.util.Random;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserRepository userRepository;

    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_";

    private String getRandomKey() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        final int quantityCharsInPassword = 10;
        for (int i = 0; i < quantityCharsInPassword; ++i) {
            sb.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }

    @Override
    public void send(String email) {
        User user = userRepository.findByUsername(email);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            mimeMessage.setFrom(new InternetAddress("andrianlarson@gmail.com"));
            mimeMessage.setSubject("Epam support group!");
            helper.setTo(user.getUsername());
            helper.setText("Hello " + user.getUsername() + " it is your temp password " + getRandomKey(), true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);
    }
}
