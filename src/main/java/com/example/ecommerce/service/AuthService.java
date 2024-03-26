package com.example.ecommerce.service;

import com.example.ecommerce.config.security.JwtUtils;
import com.example.ecommerce.dto.AuthResponse;
import com.example.ecommerce.dto.LoginDto;
import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.entity.IamObject;
import com.example.ecommerce.exceptions.ResourceNotFoundException;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.repository.IamObjectRepository;
import com.example.ecommerce.util.MessageUtil;
import com.example.ecommerce.util.Role;
import com.example.ecommerce.util.Status;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final IamObjectRepository iamObjectRepository;
    private final CustomerRepository customerRepository;
    private final JwtUtils jwtUtils;

    public AuthResponse login(LoginDto loginDto) {
        IamObject iamObject = iamObjectRepository.findByUsernameAndStatus(loginDto.getUsername(), Status.ACTIVE.getValue())
                .orElseThrow(() -> new ResourceNotFoundException(MessageUtil.USER_NOT_FOUND));

        if (!passwordEncoder.matches(loginDto.getPassword(), iamObject.getPassword())) {
            throw new BadCredentialsException("Invalid username or password.");
        }

        Long userId = null;
        Object data = null;

        if (iamObject.getRole().equalsIgnoreCase(Role.CUSTOMER.getValue())) {
            data = iamObject.getCustomers().get(0);
            userId = iamObject.getCustomers().get(0).getId();
        }
        String token = jwtUtils.createToken(userId, Role.CUSTOMER.getValue());
        return new AuthResponse(token, data);
    }

    public void registerCustomer(LoginDto loginDto) {

        IamObject iamObject = new IamObject();
        iamObject.setUsername(loginDto.getUsername());
        iamObject.setPassword(passwordEncoder.encode(loginDto.getPassword()));
        iamObject.setRole(Role.CUSTOMER.getValue());
        iamObject.setStatus(Status.ACTIVE.getValue());
        iamObjectRepository.save(iamObject);

        Customer customer = new Customer();
        customer.setIamObject(iamObject);
        customerRepository.save(customer);
    }
}
