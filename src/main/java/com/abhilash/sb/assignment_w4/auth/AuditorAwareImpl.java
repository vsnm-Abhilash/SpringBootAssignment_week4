package com.abhilash.sb.assignment_w4.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        //get Security context
        //get authentication
        //get principle
        //get username
        return Optional.of("Abhilash Vellanki");
    }
}
