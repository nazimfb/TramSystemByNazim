package az.code.trammanagementsystem.exceptions;

import az.code.trammanagementsystem.dto.ErrorInfo;
import az.code.trammanagementsystem.services.EmailService;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ConfirmationTokenExpiredException extends RuntimeException {
    private ErrorInfo errorInfo;
    static final String message = "Confirmation link expired, please check your mail for new link.";

    public ConfirmationTokenExpiredException(EmailService emailService, String email, String token) {
        super(message);
        this.errorInfo = ErrorInfo.builder().status(400).message(message).build();
        emailService.sendConfirmationEmail(email, token);
    }
}
