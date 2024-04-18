package az.code.trammanagementsystem.services;

public interface EmailService {
    void sendConfirmationEmail(String to, String confirmationToken);
    void sentMailMessage(String to, String notification);
}
