package az.code.trammanagementsystem.auth;

import lombok.Data;

@Data
public class EmailConfirmationRequest {
    private String confirmationToken;
}
