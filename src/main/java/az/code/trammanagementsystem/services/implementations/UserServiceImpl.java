package az.code.trammanagementsystem.services.implementations;

import az.code.trammanagementsystem.entity.User;
import az.code.trammanagementsystem.exceptions.InvalidUserFormatException;
import az.code.trammanagementsystem.exceptions.UserAlreadyExistsException;
import az.code.trammanagementsystem.exceptions.UserNotFoundException;
import az.code.trammanagementsystem.repository.UserRepo;
import az.code.trammanagementsystem.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static az.code.trammanagementsystem.services.helpers.UserEmailHelper.isValidEmail;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    @Override
    public User createUser(User newUser) {
        if (newUser.getEmail() == null || newUser.getUsername() == null
                || newUser.getPassword() == null || newUser.getEmail().isEmpty()
                || newUser.getUsername().isEmpty() || newUser.getPassword().isEmpty())
            throw new InvalidUserFormatException("Fields cannot be empty");

        if (!isValidEmail(newUser.getEmail()))
            throw new InvalidUserFormatException("Invalid email format");

        if (getUserByEmail(newUser.getEmail()) != null)
            throw new UserAlreadyExistsException();

        //additional password validation

        try {
            return userRepo.save(newUser);
        } catch (Exception e) {
            throw new InvalidUserFormatException(e.getMessage());
        }
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException();
        return user.get();
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User> user = userRepo.findByEmail(email);
        if (user.isEmpty())
            throw new UserNotFoundException();
        return user.get();
    }

    @Override
    public void deleteUser(Long id) {
        User user = getUserById(id);
        try {
            userRepo.delete(user);
        } catch (Exception e) {
            throw new InvalidUserFormatException(409, e.getMessage());
        }
    }

    @Override
    public boolean usernameExists(String newUserName) {
        Optional<User> user = userRepo.findByUsername(newUserName);
        return user.isPresent();
    }
}
