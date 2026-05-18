package com.example.student_management_system.service;

import com.example.student_management_system.DTO.LoginRequest;
import com.example.student_management_system.DTO.RegisterRequest;

import com.example.student_management_system.entity.User;

import com.example.student_management_system.repository.UserRepository;

import com.example.student_management_system.security.CustomUserDetailsService;

import com.example.student_management_system.security.JwtService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailsService customUserDetailsService;

    // REGISTER
    public String register(RegisterRequest request) {

        User user = new User();

        user.setName(request.getName());

        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        user.setRole(request.getRole());

        userRepository.save(user);

        return "User Registered Successfully";
    }

    // LOGIN
    public String login(LoginRequest request) {

        // AUTHENTICATE USER
        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(

                        request.getEmail(),

                        request.getPassword()
                )
        );

        // LOAD USER DETAILS
        UserDetails userDetails =

                customUserDetailsService
                        .loadUserByUsername(
                                request.getEmail()
                        );

        // GENERATE TOKEN
        return jwtService.generateToken(userDetails);
    }
}