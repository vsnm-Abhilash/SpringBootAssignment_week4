package com.abhilash.sb.assignment_w4.configs;

import com.abhilash.sb.assignment_w4.filters.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/post","/error","/auth/**").permitAll()
                        //.requestMatchers("/post/**").hasAnyRole("ADMIN")
                        .anyRequest().authenticated())
                .csrf(csrfConfig->csrfConfig.disable())
                .sessionManagement(sessionConfig->sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//                .formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }

//    @Bean
//    UserDetailsService myInMemoryUserDetailsService(){
//        UserDetails adminUser= User
//                .withUsername("abhi")
//                .password(passwordEncoder().encode("abhilash"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails normalUser= User
//                .withUsername("abhi123")
//                .password(passwordEncoder().encode("abhi"))
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(normalUser,adminUser);
//    }

//

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
