package az.code.trammanagementsystem.services.implementations;

import az.code.trammanagementsystem.entity.ConfirmationToken;
import az.code.trammanagementsystem.entity.User;
import az.code.trammanagementsystem.exceptions.ConfirmationTokenNotValidException;
import az.code.trammanagementsystem.repository.ConfirmationTokenRepo;
import az.code.trammanagementsystem.services.ConfirmationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {
    private final ConfirmationTokenRepo repo;
    public ConfirmationToken generateConfirmationToken(User user){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiryDate = now.plusDays(10);
        return repo.save(ConfirmationToken.builder()
                .expiryDate(expiryDate)
                .user(user)
                .token(UUID.randomUUID().toString())
                .build());
    }

    @Override
    public ConfirmationToken getByToken(String token) {
        Optional<ConfirmationToken> tokenOptional = repo.findByToken(token);
        if (tokenOptional.isEmpty())
            throw new ConfirmationTokenNotValidException();
        return tokenOptional.get();
    }

    @Override
    public void delete(String token) {
        ConfirmationToken confirmationToken = getByToken(token);
        try {
            repo.delete(confirmationToken);
        } catch (Exception e) {
            throw new ConfirmationTokenNotValidException(e.getMessage());
        }
    }
}

