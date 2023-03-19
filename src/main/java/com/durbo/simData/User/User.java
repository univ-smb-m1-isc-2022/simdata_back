package com.durbo.simData.User;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity(name = "users")
public class User {
    //the column need to be unique
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private Role role;
}
