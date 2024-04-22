package az.code.trammanagementsystem.services;

import az.code.trammanagementsystem.entity.User;

public interface UserService {
    User createUser(User user);
    User getUserById(Long id);

    User getUserByEmail(String email);

    void deleteUser(Long id);

    boolean usernameExists(String newUserName);
}
