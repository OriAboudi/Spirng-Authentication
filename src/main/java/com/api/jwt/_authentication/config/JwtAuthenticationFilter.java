package com.api.jwt._authentication.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
       final String authHeader = request.getHeader("Authorization");
       final String jwt;
       final String userEmail;

       // Check if Token exist and valid else throw
       if (authHeader==null || !authHeader.startsWith("Bearer ")){
           filterChain.doFilter(request,response);
           return;
       }
       // Extract the Jwt from header
       jwt = authHeader.substring(7);
       // Extract the userEmail from jwt by jwtService
       userEmail = jwtService.extractUsername(jwt);
       // If userEmail exist and token not validated yet
       if (userEmail != null &&
               SecurityContextHolder
                       .getContext()
                       .getAuthentication()==null){
           UserDetails userDetails =this.userDetailsService.loadUserByUsername(userEmail);
           if (jwtService.isTokenValid(jwt,userDetails)){
               // Create instance of authentication key for security context
               UsernamePasswordAuthenticationToken authToken  =
                       new UsernamePasswordAuthenticationToken(
                               userDetails,
                               null,
                               userDetails.getAuthorities()
                       );
               // Put the Request in the authToken
               authToken.setDetails(
                       new WebAuthenticationDetailsSource().buildDetails(request)
               );
               // To update the SecurityContext with the UserDetails
               SecurityContextHolder.getContext().setAuthentication(authToken);
           }
           // Close the circle of filter
           filterChain.doFilter(request,response);
       }


    }
}
