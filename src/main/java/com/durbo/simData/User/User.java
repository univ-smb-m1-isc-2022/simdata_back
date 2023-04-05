package com.durbo.simData.User;

import com.durbo.simData.core.metadata.MetaData;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import lombok.Data;
import lombok.ToString;

import java.util.Set;

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

    @ToString.Exclude
    @JsonBackReference
    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private Role role;
}
