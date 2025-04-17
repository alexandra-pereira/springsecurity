package com.example.javasecurity.repository;

import com.example.javasecurity.models.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAppRepository extends JpaRepository<UserApp, Integer> {
    public Optional<UserApp> findByEmail(String email);
}