package az.code.trammanagementsystem.auth;

import az.code.trammanagementsystem.exceptions.AuthException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AuthenticationController {

    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterRequest request) throws AuthException {
        return new ResponseEntity<>(service.register(request), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = service.authenticate(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refreshAccessToken(@RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(service.regenerateTokens(request));
    }

    @GetMapping(value = "/confirm", produces = "application/json")
    public ResponseEntity<EmailConfirmationResponse> confirmEmail(@RequestParam("confirmationToken") String token) {
        return new ResponseEntity<>(service.confirmEmail(token), HttpStatus.OK);
    }
}
