package com.example.blog.QT_Global_Blog.controller;

import com.example.blog.QT_Global_Blog.PayLoad.AuthenticationRequest;
import com.example.blog.QT_Global_Blog.PayLoad.AuthenticationResponse;
import com.example.blog.QT_Global_Blog.PayLoad.RegisterRequest;
import com.example.blog.QT_Global_Blog.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthService service;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {

        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {

        return ResponseEntity.ok(service.authenticate(request));
    }
}
