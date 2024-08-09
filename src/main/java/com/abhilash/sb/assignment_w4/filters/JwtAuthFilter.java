package com.abhilash.sb.assignment_w4.filters;

import com.abhilash.sb.assignment_w4.entities.User;
import com.abhilash.sb.assignment_w4.services.JWTService;
import com.abhilash.sb.assignment_w4.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            final String requestTokenHeader=request.getHeader("Authorization");
            if(requestTokenHeader ==null || !requestTokenHeader.startsWith("Bearer")){
                filterChain.doFilter(request,response);
                return;
            }
            String token=requestTokenHeader.split("Bearer ")[1];
            Long userId= jwtService.getUserIdFromToken(token);
            if(userId!=null){
                User user=userService.getUserByUserId(userId);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                        new UsernamePasswordAuthenticationToken(user,null,null);
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }
            filterChain.doFilter(request,response);

    }
}
