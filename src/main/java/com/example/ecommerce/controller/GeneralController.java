package com.example.ecommerce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("general")
public class GeneralController {


    @GetMapping("user")
    public String getUser() {
        System.out.println("GETTING USERS");
        return "Users";
    }

    @GetMapping("current-user")
    public String getCurrentUser(Principal principal) {
        return principal.getName();
    }
}
