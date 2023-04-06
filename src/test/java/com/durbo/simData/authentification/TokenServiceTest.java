package com.durbo.simData.authentification;

import com.durbo.simData.User.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TokenServiceTest {



    @Test
    public void testTokenService() {
        TokenService tokenService = new TokenService();
        assert tokenService != null;
    }

    @Test
    public void testGenerateToken() {
        TokenService tokenService = new TokenService();
        User user = new User();
        assert tokenService.generateToken(user) != null;
    }

    @Test
    public void testValidateToken() {
        TokenService tokenService = new TokenService();
        User user = new User();
        String token = tokenService.generateToken(user);
        assert tokenService.validateToken(token, user);
    }

}
