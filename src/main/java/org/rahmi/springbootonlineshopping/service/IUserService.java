package org.rahmi.springbootonlineshopping.service;

import org.rahmi.springbootonlineshopping.model.UserEntity;
import org.springframework.http.ResponseEntity;

public interface IUserService {

    ResponseEntity<String> saveUser(UserEntity user);

    boolean  existsByUsername(String usernName);

    UserEntity getUserByUsername(String usernName);
}


