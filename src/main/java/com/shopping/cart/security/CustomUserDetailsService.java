package com.shopping.cart.security;

import com.shopping.cart.entity.User;
import com.shopping.cart.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (!userRepository.existsByEmailIgnoreCase(s)) {
            throw new UsernameNotFoundException("User with email " + s + " not found.");
        }
        User userFromDb = userRepository.getByEmail(s);
        return new CustomUserDetails(userFromDb);
    }
}
