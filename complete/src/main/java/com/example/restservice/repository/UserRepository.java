package com.example.restservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.restservice.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}