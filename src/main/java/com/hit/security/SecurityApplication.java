package com.hit.security;

import com.hit.security.models.Account;
import com.hit.security.models.Role;
import com.hit.security.repository.AccountRepository;
import com.hit.security.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class SecurityApplication {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

    @Bean
    CommandLineRunner init() {
        return args -> {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            if (roleRepository.count() == 0) {
                roleRepository.save(new Role("ROLE_ADMIN", null));
                roleRepository.save(new Role("ROLE_USER", null));
            }

            if (accountRepository.count() == 0) {
                Account account = new Account("Admin", "admin",
                        passwordEncoder.encode("admin"), Set.copyOf(roleRepository.findAll()));
                accountRepository.save(account);
            }
        };
    }
}
