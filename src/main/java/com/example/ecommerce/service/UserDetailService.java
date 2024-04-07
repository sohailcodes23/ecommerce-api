package com.example.ecommerce.service;

import com.example.ecommerce.config.security.JwtUtils;
import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserDetailService {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private JwtUtils jwtUtils;

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