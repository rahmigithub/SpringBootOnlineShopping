package org.rahmi.springbootonlineshopping.service.Impl;

import org.rahmi.springbootonlineshopping.model.UserEntity;
import org.rahmi.springbootonlineshopping.repository.UserRepository;
import org.rahmi.springbootonlineshopping.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements IUserService {

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<String> saveUser(UserEntity user) {

        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok("User Registered");
    }

    @Override
    public boolean existsByUsername(String usernName) {

        return userRepository.existsByUsername(usernName);

    }

    @Override
    public UserEntity getUserByUsername(String usernName) {


        return userRepository.findByUsername(usernName).orElseThrow(() -> new UsernameNotFoundException("Username not found"));

    }
}
