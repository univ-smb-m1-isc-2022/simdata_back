package com.durbo.simData.authentification;


import com.durbo.simData.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TokenServiceTest {

    TokenService tokenService;
    User user;

    @BeforeEach
    public void setUp() {
        tokenService = new TokenService();
        user = new User();
        user.setUsername("Test");
        user.setEmail("test@mail.com");
        user.setPassword("test");
    }

    @Test
    public void testGenerateToken() {
        assert tokenService.generateToken(user) != null;
    }

    @Test
    public void testValidateToken() {
        String token = tokenService.generateToken(user);
        assert tokenService.validateToken(token, user);
    }
}
