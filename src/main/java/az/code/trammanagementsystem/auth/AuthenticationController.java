package az.code.trammanagementsystem.auth;

import az.code.trammanagementsystem.exceptions.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) throws AuthException {
        AuthenticationResponse response = service.register(request);
        if (response == null) {
            throw new AuthException(409,"Mail already registered");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = service.authenticate(request);
        if (response == null) {
            throw new AuthException(401,"Username/email or password is wrong");
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refreshAccessToken(@RequestBody RefreshTokenRequest request) {
        AuthenticationResponse response = service.regenerateTokens(request);
        if (response == null) {
            throw new AuthException(401,"Token is not valid");
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/confirm", produces = "application/json")
    public ResponseEntity<EmailConfirmationResponse> confirmEmail(@RequestParam("confirmationToken") String token) {
        EmailConfirmationResponse response = service.confirmEmail(token);
        if (response==null) {
            throw new AuthException(400,"Already confirmed");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
