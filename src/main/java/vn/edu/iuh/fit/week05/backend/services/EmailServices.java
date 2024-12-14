package vn.edu.iuh.fit.week05.backend.services;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServices {
    private final JavaMailSenderImpl mailSender;

    public EmailServices(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String subject, String text) {
        // Cấu hình gửi email, có thể sử dụng thư viện như JavaMail hoặc Spring Boot Mail
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
