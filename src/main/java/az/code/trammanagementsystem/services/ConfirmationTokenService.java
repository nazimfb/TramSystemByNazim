package az.code.trammanagementsystem.services;

import az.code.trammanagementsystem.entity.ConfirmationToken;
import az.code.trammanagementsystem.entity.User;

public interface ConfirmationTokenService {
    ConfirmationToken generateConfirmationToken(User user);
    ConfirmationToken getByToken(String token);
    void delete(ConfirmationToken token);
}
