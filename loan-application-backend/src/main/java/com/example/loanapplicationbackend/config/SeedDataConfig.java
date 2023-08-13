package com.example.loanapplicationbackend.config;

import com.example.loanapplicationbackend.model.Role;
import com.example.loanapplicationbackend.model.User;
import com.example.loanapplicationbackend.repository.UserRepository;
import com.example.loanapplicationbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeedDataConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {

            User admin = User
                    .builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin"))
                    .role(Role.ROLE_ADMIN)
                    .build();

            userService.save(admin);
            System.out.println("created ADMIN user - {}" + admin);
            log.debug("created ADMIN user - {}", admin);
        }
    }
}
