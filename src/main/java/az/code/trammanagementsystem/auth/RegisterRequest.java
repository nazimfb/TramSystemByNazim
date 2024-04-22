package az.code.trammanagementsystem.auth;

import az.code.trammanagementsystem.validation.UniqueUsername;
import az.code.trammanagementsystem.validation.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @UniqueUsername
    private String username;
    private String email;
    @ValidPassword
    private String password;
}
