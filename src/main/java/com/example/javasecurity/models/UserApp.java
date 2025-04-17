package com.example.javasecurity.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user_app")
public class UserApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String email;
    private String password;
    private String role;

    public UserApp(String email, String password, String role)  {
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
