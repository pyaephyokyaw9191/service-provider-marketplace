package com.eventorganizer.auth.service;

import com.eventorganizer.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Loading user by username: " + username);
        return userRepository.findByUsername(username)
                .map(user -> {
                    System.out.println("User found: " + user.getUsername() + ", role: " + user.getRole());
                    return new User(
                            user.getUsername(),
                            user.getPassword(),
                            Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
                    );
                })
                .orElseThrow(() -> {
                    System.out.println("User not found: " + username);
                    return new UsernameNotFoundException("User not found");
                });
    }
} 