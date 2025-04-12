
package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import com.example.restservice.model.User;
import com.example.restservice.repository.UserRepository;


    


        @RestController
        @Validated
        public class UserController {



            @Autowired
            private UserRepository userRepository;

   

            @PostMapping("/usuarios")
            public User createUser(@Valid @RequestBody User user) {
                return userRepository.save(user);
            }

            @GetMapping("/usuarios/{id}")
            @Cacheable(value = "users", key = "#id")
            public User getUser(@PathVariable Long id) {
                return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
            }

            @PutMapping("/usuarios/{id}")
            @CachePut(value = "users", key = "#id")
            public User updateUser(@PathVariable Long id, @Valid @RequestBody User userDetails) {
                User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
                user.setName(userDetails.getName());
                user.setEmail(userDetails.getEmail());
                return userRepository.save(user);
            }

            @DeleteMapping("/usuarios/{id}")
            @CacheEvict(value = "users", key = "#id")
            public void deleteUser(@PathVariable Long id) {
                userRepository.deleteById(id);
            }
        }
	

