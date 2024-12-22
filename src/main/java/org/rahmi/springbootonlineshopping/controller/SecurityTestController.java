package org.rahmi.springbootonlineshopping.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class SecurityTestController {


    @GetMapping("hasRole")
    public ResponseEntity getTestHasRole(){

        return ResponseEntity.ok("Has role worked");

    }
}
