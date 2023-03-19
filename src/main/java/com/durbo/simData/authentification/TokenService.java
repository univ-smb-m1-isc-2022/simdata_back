package com.durbo.simData.authentification;

import com.durbo.simData.User.User;
import com.durbo.simData.User.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Data
@Service
public class TokenService {

    @Autowired
    private UserService userService;

    public String generateToken(User userToLogin) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String tokenData = userToLogin.getId() + userToLogin.getEmail();
            byte[] tokenBytes = md.digest(tokenData.getBytes());
            String token = Base64.getEncoder().encodeToString(tokenBytes);
            return token.replace("/", "_");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean validateToken(String token, User user) {
        String tokenToValidate = generateToken(user);
        return token.equals(tokenToValidate);
    }

    public User getUserFromToken(String token) {
        Iterable<User> users = userService.getUsers();
        for (User user : users) {
            if (validateToken(token, user)) {
                return user;
            }
        }
        return null;
    }
}
