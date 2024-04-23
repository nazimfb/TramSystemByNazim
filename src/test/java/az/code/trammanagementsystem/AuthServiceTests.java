package az.code.trammanagementsystem;

import az.code.trammanagementsystem.auth.AuthService;
import az.code.trammanagementsystem.auth.RefreshTokenRequest;
import az.code.trammanagementsystem.auth.RegisterRequest;
import az.code.trammanagementsystem.config.JwtService;
import az.code.trammanagementsystem.exceptions.AuthException;
import az.code.trammanagementsystem.exceptions.InvalidUserFormatException;
import az.code.trammanagementsystem.services.EmailService;
import az.code.trammanagementsystem.services.ConfirmationTokenService;
import az.code.trammanagementsystem.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTests {

    @Mock
    private UserRepo userRepo;

    @Mock
    private EmailService emailService;

    @Mock
    private ConfirmationTokenService confirmationTokenService;

    @Mock
    private JwtService jwtService;  // Mocked JwtService

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private UserDetailsService userDetailsService;

    @InjectMocks
    private AuthService authService;
//    manual injecting
//    private AuthService authService = new AuthService(passwordEncoder, userRepo, jwtService, authenticationManager, userDetailsService, emailService, confirmationTokenService);

    @Test
    public void testRegisterWithInvalidEmailFormat() {
        RegisterRequest request = new RegisterRequest("username", "invalid_email", "password");
        assertThrows(InvalidUserFormatException.class, () -> authService.register(request));
    }

    @Test
    public void testRegenerateTokens_UnexpectedError() {
        RefreshTokenRequest request = new RefreshTokenRequest();
        request.setRefreshToken("invalid_refresh_token");

        assertThrows(AuthException.class, () -> authService.regenerateTokens(request));
    }
}
