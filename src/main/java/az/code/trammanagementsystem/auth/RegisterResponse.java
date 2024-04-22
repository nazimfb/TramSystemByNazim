package az.code.trammanagementsystem.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    @Builder.Default
    private String status = "success";
    @Builder.Default
    private String message = "User successfully registered";
    private Long userId;
    private String username;
    private String userEmail;
}

/*
{
  "status": "success",
  "message": "User successfully registered",
  "user": {
    "id": "123456789",
    "username": "example_user",
    "email": "user@example.com"
    // Other user details...
  }
}
*/