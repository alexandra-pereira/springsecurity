package com.example.javasecurity.services;

import com.example.javasecurity.models.UserApp;
import com.example.javasecurity.repository.UserAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserAppRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createUser(String username, String rawPassword) {
        // encode le mot de passe avant de sauvegarder
        String encoded = passwordEncoder.encode(rawPassword);
        repo.save(new UserApp(username, encoded,  "USER"));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername");
        UserApp user = repo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return User.builder()
                .username(user.getEmail())
                // on passe directement le mot de passe déjà encodé en BDD
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }

    public void createAdmin(String username, String rawPassword) {
        String encoded = passwordEncoder.encode(rawPassword);
        repo.save(new UserApp(username, encoded,  "ADMIN"));
    }
}
