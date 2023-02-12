package com.durbo.simData.controller;

import com.durbo.simData.model.User;
import com.durbo.simData.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public Iterable<User> getUsers() {
        log.info("Getting users");
        return userService.getUsers();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id) {
        log.info("Getting user");
        Optional<User> user = userService.getUser(id);
        return user.orElse(null);
    }

    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable Long id, User user) {
        log.info("Updating user");
        Optional<User> userOptional = userService.getUser(id);
        if (userOptional.isPresent()) {
            User userToUpdate = userOptional.get();
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setPassword(user.getPassword());
            userToUpdate.setEmail(user.getEmail());
            return userService.saveUser(userToUpdate);
        }
        return null;
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        log.info("Creating user");
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        log.info("Logging in");
        return userService.login(user);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        log.info("Deleting user");
        userService.deleteUser(id);
    }
}
