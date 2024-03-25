package com.example.ecommerce.controller;

import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.LoginDto;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@Scope(value = "request")
@RequestMapping("v1/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("login")
    private ResponseEntity<AuthResponse> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(authService.login(loginDto));
    }

}
