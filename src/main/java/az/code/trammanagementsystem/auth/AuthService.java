package az.code.trammanagementsystem.auth;

import az.code.trammanagementsystem.config.JwtService;
import az.code.trammanagementsystem.entity.ConfirmationToken;
import az.code.trammanagementsystem.entity.Role;
import az.code.trammanagementsystem.entity.User;
import az.code.trammanagementsystem.exceptions.*;
import az.code.trammanagementsystem.repository.UserRepo;
import az.code.trammanagementsystem.services.ConfirmationTokenService;
import az.code.trammanagementsystem.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static az.code.trammanagementsystem.services.helpers.UserEmailHelper.*;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final EmailService emailService;
    private final ConfirmationTokenService confirmationTokenService;

    public RegisterResponse register(RegisterRequest request) {
        if (request == null || !isValidEmail(request.getEmail()))
            throw new InvalidUserFormatException("Invalid email format");

        if (userRepo.findByEmail(request.getEmail()).isPresent())
            throw new AuthException(409, "Mail already registered");

        try {
            var user = User.builder()
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.USER)
                    .verified(false)
                    .build();
            userRepo.save(user);

            emailService.sendConfirmationEmail(
                    user.getEmail(),
                    confirmationTokenService.generateConfirmationToken(user)
                            .getToken());

            return RegisterResponse.builder()
                    .userEmail(user.getEmail())
                    .userId(user.getId())
                    .username(user.getUsername())
                    .build();
        } catch (Exception e) {
//            log.error(e.getMessage());
            throw new AuthException(e.getMessage());
        }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new AuthException("User not found"));
        if (!user.isVerified()) {
            String maskedEmail = getMaskedEmail(user).toString();
            throw new AuthException("Please verify your email: " + maskedEmail);
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()));

            var jwtToken = jwtService.generateAccessToken(user);
            var refreshToken = jwtService.generateRefreshToken(user);

            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .build();
        } catch (Exception e) {
            throw new AuthException(e.getMessage());
        }
    }

    public AuthenticationResponse regenerateTokens(RefreshTokenRequest request) {
//        request.getAccessToken();
        String refreshToken = request.getRefreshToken();
        if (refreshToken == null || refreshToken.isEmpty())
            throw new AuthException(400, "Token can not be empty");

        if (jwtService == null) {
            throw new AuthException(500, "JWT Service is not initialized");
        }

        if (!jwtService.isExpired(refreshToken)
                && jwtService.extractTokenType(refreshToken) != null
                && jwtService.extractTokenType(refreshToken).equalsIgnoreCase("refresh")) {
            try {
                String username = jwtService.extractUsername(refreshToken);
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                String newAccessToken = jwtService.generateAccessToken(userDetails);
                String newRefreshToken = jwtService.generateRefreshToken(userDetails);

                return AuthenticationResponse.builder()
                        .accessToken(newAccessToken)
                        .refreshToken(newRefreshToken)
                        .build();
            } catch (Exception e) {
                throw new AuthException(e.getMessage());
            }
        } else {
            throw new AuthException(401, "Refresh token is not valid");
        }
    }

    public EmailConfirmationResponse confirmEmail(String request) {
        ConfirmationToken confirmationToken = confirmationTokenService.getByToken(request);

        String email = confirmationToken.getUser().getEmail();
        User user = userRepo.findByEmail(email).orElseThrow();

        if (user.isVerified())
            throw new UserAlreadyVerifiedException(email);

        if (confirmationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            confirmationTokenService.delete(confirmationToken.getToken());
            throw new ConfirmationTokenExpiredException(
                    emailService,
                    email,
                    confirmationTokenService.generateConfirmationToken(user).getToken());
        }

        user.setVerified(true);
        userRepo.save(user);

        confirmationTokenService.delete(confirmationToken.getToken());
        return EmailConfirmationResponse
                .builder()
                .confirmed(true)
                .email(email)
                .build();
    }
}
