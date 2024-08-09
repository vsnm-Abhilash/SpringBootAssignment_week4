package com.abhilash.sb.assignment_w4.services;

import com.abhilash.sb.assignment_w4.dto.LoginDTO;
import com.abhilash.sb.assignment_w4.dto.SignUpDTO;
import com.abhilash.sb.assignment_w4.dto.UserDTO;
import com.abhilash.sb.assignment_w4.entities.User;
import com.abhilash.sb.assignment_w4.exceptions.ResourceNotFoundException;
import com.abhilash.sb.assignment_w4.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(()->new ResourceNotFoundException("User with email "+username+ " not found!"));
    }

    public User getUserByUserId(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User with userID "+userId+ " not found!"));
    }

    public UserDTO signUp(SignUpDTO signUpDTO) {
        Optional<User> userCheck=userRepository.findByEmail(signUpDTO.getEmail());
        if(userCheck.isPresent()){
            throw new BadCredentialsException("User with email "+signUpDTO.getEmail()+" already exists!");
        }
        User user=mapper.map(signUpDTO,User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return mapper.map(userRepository.save(user),UserDTO.class);

    }

}
