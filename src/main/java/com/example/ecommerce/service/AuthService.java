package com.example.ecommerce.service;

import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.LoginDto;
import com.example.demo.entity.Customer;
import com.example.demo.entity.IamObject;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.IamObjectRepository;
import com.example.demo.util.JwtUtils;
import com.example.demo.util.MessageUtil;
import com.example.demo.util.Roles;
import com.example.demo.util.Status;
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
        IamObject iamObject = iamObjectRepository.findByEmailAndStatus(loginDto.getUsername(), Status.Active)
                .orElseThrow(() -> new ResourceNotFoundException(MessageUtil.USER_NOT_FOUND));

        if (!passwordEncoder.matches(loginDto.getPassword(), iamObject.getPassword())) {
            throw new BadCredentialsException("Invalid username or password.");
        }

        Long userId = null;
        Object data = null;

        if (iamObject.getRole().equalsIgnoreCase(Roles.CUSTOMER)) {
            data = iamObject.getCustomers().get(0);
            userId = iamObject.getCustomers().get(0).getId();
        }
        String token = jwtUtils.createToken(userId, Roles.CUSTOMER);
        return new AuthResponse(token, data);
    }

    public void registerCustomer(LoginDto loginDto) {

        IamObject iamObject = new IamObject();
        iamObject.setEmail(loginDto.getUsername());
        iamObject.setPassword(passwordEncoder.encode(loginDto.getPassword()));
        iamObject.setRole(Roles.CUSTOMER);
        iamObject.setStatus(Status.Active);
        iamObjectRepository.save(iamObject);

        Customer customer = new Customer();
        customer.setIamObject(iamObject);
        customerRepository.save(customer);
    }
}
