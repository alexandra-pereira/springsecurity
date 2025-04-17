package com.example.javasecurity;

import com.example.javasecurity.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // CSRF désactivé (pour H2 console + POST /register)
                .csrf(csrf -> csrf.disable())
                // autorisations
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/hello/public",
                                "/h2-console/**",
                                "/login",
                                "/register",
                                "/user-app/register"
                        ).permitAll()
                        //.requestMatchers("/hello/private").authenticated()
                        .requestMatchers("/hello/user").hasRole("USER")
                        .requestMatchers("/hello/admin").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                // form login
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/hello/private", true)
                        .failureUrl("/login?error")
                        .permitAll()
                )

                // logout
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                )
                // pour autoriser l'affichage de la console H2 en iframe
                .headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
}