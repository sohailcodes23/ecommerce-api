package com.example.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class AppConfig {

    //UserDetailsService : Service provided by Spring Security
    @Bean
    public UserDetailsService userDetailsService() {
//        User: Class provided by Spring Security
        //Default users
        UserDetails user1 = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        UserDetails user2 = User
                .builder()
                .username("support")
                .password(passwordEncoder().encode("support"))
                .roles("SUPPORT")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);// creating a In memory user, can be used on spring security login page
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

