package com.sleepkqq.employeesystem.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Component
public class DeveloperRepositorySingleton {

    private static DeveloperRepository developerRepository;

    @Autowired
    public DeveloperRepositorySingleton(DeveloperRepository developerRepository) {
        DeveloperRepositorySingleton.developerRepository = developerRepository;
    }

    public static DeveloperRepository getInstance() {
        return developerRepository;
    }

    @Bean
    public static PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
