package az.code.trammanagementsystem.services.implementations;

import az.code.trammanagementsystem.entity.ConfirmationToken;
import az.code.trammanagementsystem.entity.User;
import az.code.trammanagementsystem.repository.ConfirmationTokenRepo;
import az.code.trammanagementsystem.services.ConfirmationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {
    private final ConfirmationTokenRepo repo;
    public ConfirmationToken generateConfirmationToken(User user){
        LocalDateTime now = LocalDateTime.now();
        return repo.save(ConfirmationToken.builder()
                .expiryDate(LocalDateTime.of(
                        now.getYear(),
                        now.getMonth(),
                        now.getDayOfMonth() + 10,
                        0,0))
                .user(user)
                .token(UUID.randomUUID().toString())
                .build());
    }

    @Override
    public ConfirmationToken getByToken(String token) {
        return repo.findByToken(token).orElse(null);
    }

    @Override
    public void delete(ConfirmationToken token) {
        repo.delete(token);
    }
}

