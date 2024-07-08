package com.cactt4ck.caccrm.service;

import com.cactt4ck.caccrm.model.CacAccount;
import com.cactt4ck.caccrm.model.LoginUserDto;
import com.cactt4ck.caccrm.model.RegisterUserDto;
import com.cactt4ck.caccrm.repository.CacAccountRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthentificationService {

    private final CacAccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthentificationService(CacAccountRepository accountRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public CacAccount signup(RegisterUserDto input) {
        CacAccount user = CacAccount.builder()
                .fullname(input.getUsername())
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .build();

        return accountRepository.save(user);
    }

    public CacAccount authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return accountRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }

}
