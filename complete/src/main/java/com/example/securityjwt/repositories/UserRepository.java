package com.example.securityjwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.securityjwt.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}