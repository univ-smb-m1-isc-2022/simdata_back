package com.durbo.simData.User;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTests {

        @Test
        public void testUser() {
            User user = new User();
            user.setUsername("test");
            user.setPassword("test");
            user.setEmail("test@mail.com");
            user.setRole(Role.USER);
            assert user.getUsername().equals("test");
            assert user.getPassword().equals("test");
            assert user.getEmail().equals("test@mail.com");
            assert user.getRole().toString().equals("USER");
        }
}
