package com.example.ecommerce.service;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.IamObjectRepository;
import com.example.demo.util.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@AllArgsConstructor
public class IamObjectService {
    private final IamObjectRepository iamObjectRepository;
    private final CustomerRepository customerRepository;

    private final JwtUtils jwtUtils;

    public Customer getCustomer(Principal principal) {
        Long userId = Long.parseLong(principal.getName());
        return customerRepository.findById(userId)
                .orElseThrow(() -> new BadCredentialsException("User not found"));
    }

    public Customer retrieve(String token) {
        Long id = Long.parseLong(jwtUtils.verify(token).getSubject());
        return customerRepository.findById(id)
                .orElseThrow(() -> new BadCredentialsException("Invalid username or password."));
    }
}