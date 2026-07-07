package com.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final String username;
    private final String encodedPassword;

    public CustomUserDetailsService(PasswordEncoder passwordEncoder,
                                    @Value("${oauth2.user.username}") String username,
                                    @Value("${oauth2.user.password}") String password) {
        this.username = username;
        this.encodedPassword = passwordEncoder.encode(password);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!this.username.equals(username)) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return User.builder()
                .username(this.username)
                .password(encodedPassword)
                .roles("USER")
                .build();
    }
}
