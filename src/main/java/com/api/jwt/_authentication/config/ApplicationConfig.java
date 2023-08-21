package com.api.jwt._authentication.config;

import com.api.jwt._authentication.user.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final UserRepository repository;
    @Bean
    public UserDetailsService userDetailsService(){
        return username ->repository.findByEmail(username)
                .orElseThrow(()->new UsernameNotFoundException("User Not Found"));
    }
}
