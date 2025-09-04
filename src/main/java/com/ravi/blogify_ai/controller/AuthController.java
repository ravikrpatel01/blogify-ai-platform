package com.ravi.blogify_ai.controller;

import com.ravi.blogify_ai.config.JwtProvider;
import com.ravi.blogify_ai.entity.User;
import com.ravi.blogify_ai.repository.UserRepository;
import com.ravi.blogify_ai.request.LoginRequest;
import com.ravi.blogify_ai.request.UserRequest;
import com.ravi.blogify_ai.response.AuthResponse;
import com.ravi.blogify_ai.service.CustomUserDetailsService;
import com.ravi.blogify_ai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody UserRequest user) throws Exception {
        User existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser != null) {
            throw new Exception("This email already used with another account!");
        }

        User newUser = new User();

        newUser.setFullName(user.getFullName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        User createdUser = userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(createdUser.getEmail(), createdUser.getPassword());

        String token = JwtProvider.generateToken(authentication);

        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setMessage("User registered successfully!");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) throws Exception {
        Authentication authentication = authenticate(request.getEmail(), request.getPassword());

        String token = JwtProvider.generateToken(authentication);

        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setMessage("User logged-in successfully!");

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    private Authentication authenticate(String email, String password) throws Exception {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

        if (userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new Exception("Invalid username or password!");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
