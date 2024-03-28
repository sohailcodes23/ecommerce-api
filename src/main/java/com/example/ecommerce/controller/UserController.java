package com.example.ecommerce.controller;

import com.example.ecommerce.service.IamObjectService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController()
@Scope(value = "request")
@RequestMapping("users")
@AllArgsConstructor
public class UserController {

    private final IamObjectService iamObjectService;

    @GetMapping
    private ResponseEntity test(Principal principal) {
        System.out.println("ID " + principal.getName());
        return ResponseEntity.ok(iamObjectService.getCustomer(principal));
    }

}
