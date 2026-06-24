package com.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final String HARDCODED_USERNAME = "john";
    private static final String HARDCODED_PASSWORD_HASH =
            "$2a$10$1pVWw3csE.98E42E6qZoz.NEVQz5H0qEaZhugjXZjYyKTxPl/OG2a";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!HARDCODED_USERNAME.equals(username)) {
            throw new UsernameNotFoundException("Invalid username: " + username);
        }

        return User.builder()
                .username(HARDCODED_USERNAME)
                .password(HARDCODED_PASSWORD_HASH)
                .roles("USER")
                .build();
    }
}
