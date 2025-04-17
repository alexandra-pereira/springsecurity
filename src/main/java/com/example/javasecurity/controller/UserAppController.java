package com.example.javasecurity.controller;

import com.example.javasecurity.models.UserApp;
import com.example.javasecurity.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-app")
public class UserAppController  {

    @Autowired
        CustomUserDetailsService customUserDetailsService;
    @PostMapping("/register")
        public String createUser(@ModelAttribute UserApp userApp) throws Exception {
            customUserDetailsService.createUser(
                userApp.getEmail(),
                userApp.getPassword()
            );
        return "utilisateur créé"; // Correspond au fichier login.html dans /templates
    }
}