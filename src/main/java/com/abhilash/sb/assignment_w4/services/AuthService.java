package com.abhilash.sb.assignment_w4.services;

import com.abhilash.sb.assignment_w4.dto.LoginDTO;
import com.abhilash.sb.assignment_w4.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public String logIn(LoginDTO loginDTO) {
        Authentication authentication=authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword()));
        User user=(User) authentication.getPrincipal();
        return jwtService.generateToken(user);
    }

}
