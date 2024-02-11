package com.example.squiz.controllers;

import com.example.squiz.auth.JwtService;
import com.example.squiz.dtos.AuthRequest;
import com.example.squiz.entities.UserInfoEB;
import com.example.squiz.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "${api.prefix}")
public class AuthController {
    private final UserInfoService userInfoService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(UserInfoService userInfoService,
                          JwtService jwtService,
                          AuthenticationManager authenticationManager) {
        this.userInfoService = userInfoService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/auth/addNewUser")
    public ResponseEntity<Map<String, String>> addNewUser(@RequestBody UserInfoEB userInfo) {
        Map<String, String> response = new HashMap<>();
        response.put("message", userInfoService.addUser(userInfo));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/auth/generateToken")
    public ResponseEntity<Map<String, String>> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(),
                        authRequest.getUserPassword()));
        if (authentication.isAuthenticated()) {
            Map<String, String> response = new HashMap<>();
            response.put("token", jwtService.generateToken(authRequest.getUserName()));
            return ResponseEntity.ok(response);
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
}
