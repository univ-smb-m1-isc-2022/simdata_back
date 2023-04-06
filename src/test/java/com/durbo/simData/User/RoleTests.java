package com.durbo.simData.User;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoleTests {

        @Test
        public void testRole() {
            Role role = Role.ADMIN;
            assert role.toString().equals("ADMIN");
            role = Role.USER;
            assert role.toString().equals("USER");
        }
}
