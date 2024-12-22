package org.rahmi.springbootonlineshopping.controller;

import org.rahmi.springbootonlineshopping.dto.AuthResponseDTO;
import org.rahmi.springbootonlineshopping.dto.LoginDto;
import org.rahmi.springbootonlineshopping.dto.RegisterDto;
import org.rahmi.springbootonlineshopping.model.Role;
import org.rahmi.springbootonlineshopping.model.UserEntity;
import org.rahmi.springbootonlineshopping.repository.RoleRepository;
import org.rahmi.springbootonlineshopping.repository.UserRepository;
import org.rahmi.springbootonlineshopping.security.JWTGenerator;
import org.rahmi.springbootonlineshopping.service.IUserService;
import org.rahmi.springbootonlineshopping.service.Impl.RoleServiceImp;
import org.rahmi.springbootonlineshopping.service.Impl.UserServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private  final UserServiceImp userServiceImp;
    private final  RoleServiceImp roleServiceImp;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator jwtGenerator;


    public AuthController(AuthenticationManager authenticationManager, UserServiceImp userServiceImp, RoleServiceImp roleServiceImp, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userServiceImp = userServiceImp;
        this.roleServiceImp = roleServiceImp;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDto registerDto) {

        if (userServiceImp.existsByUsername(registerDto.getUserName())) {
            return new ResponseEntity<>("UserName is already exist", HttpStatus.BAD_REQUEST);
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registerDto.getUserName());
        userEntity.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Role roles = roleServiceImp.findRoleByName("USER").get();
        userEntity.setRoles(Collections.singletonList(roles));


        return userServiceImp.saveUser(userEntity);
    }


    @PostMapping("login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto) {
        if (userServiceImp.existsByUsername(loginDto.getUserName())) {

            UserEntity userEntity = userServiceImp.getUserByUsername(loginDto.getUserName());
            if (passwordEncoder.matches(loginDto.getPassword(), userEntity.getPassword())) {
                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String token = jwtGenerator.generateToken(authentication);
               return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
                //  return new ResponseEntity<>("Login success", HttpStatus.OK);
            }
            return new ResponseEntity<>("Invalid password", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Invalid userName", HttpStatus.BAD_REQUEST);
    }

}
