package com.abhilash.sb.assignment_w4.controllers;

import com.abhilash.sb.assignment_w4.dto.LoginDTO;
import com.abhilash.sb.assignment_w4.dto.SignUpDTO;
import com.abhilash.sb.assignment_w4.dto.UserDTO;
import com.abhilash.sb.assignment_w4.services.AuthService;
import com.abhilash.sb.assignment_w4.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUpUser(@RequestBody SignUpDTO signUpDTO){
        UserDTO user= userService.signUp(signUpDTO);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> logIn(@RequestBody LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response){
        String token= authService.logIn(loginDTO);

        Cookie cookie=new Cookie("Authorization",token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return ResponseEntity.ok(token);
    }

}
