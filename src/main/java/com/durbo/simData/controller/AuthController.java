package com.durbo.simData.controller;

import com.durbo.simData.model.User;
import com.durbo.simData.model.enums.Role;
import com.durbo.simData.service.TokenService;
import com.durbo.simData.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;




@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    //create a struct to hold the credentials
        public record Credentials(String email, String password) {}
        public record Answer(User user, String token) {}

        public record NewUser(String username, String email, String password) {}
        @Autowired
        private UserService userService;

        @Autowired
        private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<Answer> login(@RequestBody @NotNull Credentials credentials, HttpServletResponse response) {
        log.info("Logging in user");
        log.debug("Credentials: " + credentials.email() + " " + credentials.password());
        log.debug("RESPONSE: " + response);
        User userToLogin = userService.login(credentials.email(), credentials.password());
        if (userToLogin == null) {
            //return null
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        //Generate a token and add it to the reponse as a cookie
        String token = tokenService.generateToken(userToLogin);
        return new ResponseEntity<>(new Answer(userToLogin,token), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<Answer> register(@RequestBody @NotNull NewUser newUser) {
        log.info("Registering user");
        log.debug("New User: " + newUser.username() + " " + newUser.email() + " " + newUser.password());
        User user = new User();
        user.setUsername(newUser.username());
        user.setEmail(newUser.email());
        user.setPassword(newUser.password());
        user.setRole(Role.USER);// TODO: check role in request
        User userToRegister = userService.saveUser(user);
        String token = tokenService.generateToken(userToRegister);
        return new ResponseEntity<>(new Answer(userToRegister,token), HttpStatus.OK);
    }
}
