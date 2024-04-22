package az.code.trammanagementsystem.services.helpers;

import az.code.trammanagementsystem.entity.User;

public class UserEmailHelper {
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    public static StringBuilder getMaskedEmail(User user) {
        String email = user.getEmail();
        int atIndex = email.indexOf("@");
        StringBuilder maskedEmail = new StringBuilder();
        maskedEmail.append(email.charAt(0));

        maskedEmail.append("*".repeat(Math.max(0, atIndex - 1)));

        // Append the remaining characters after '@'
        maskedEmail.append(email.substring(atIndex));
        return maskedEmail;
    }
}
