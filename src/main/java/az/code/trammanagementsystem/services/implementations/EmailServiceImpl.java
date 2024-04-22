package az.code.trammanagementsystem.services.implementations;

import az.code.trammanagementsystem.services.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    String supportEmail;

    @Override
    public void sendConfirmationEmail(String to, String confirmationToken) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(supportEmail);
        message.setTo(to);
        message.setSubject("Confirmation Email");
        message.setText("Welcome to our application! Please confirm your email address by clicking the link below:\n" +
                "http://localhost:8080/api/v1/auth/confirm?confirmationToken=" + confirmationToken);
        javaMailSender.send(message);
    }
}
