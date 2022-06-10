package com.ironhack.userservice.model;

import com.ironhack.userservice.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    private String email;
    private String username;
    private String fullName;
    private String image;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String email, String username, String fullName, String image) {
        this.email = email;
        this.username = username;
        this.fullName = fullName;
        this.image = image;
        this.role = Role.USER;
    }

    public User(String email) {
        this.email = email;
        this.role = Role.USER;
    }
}
