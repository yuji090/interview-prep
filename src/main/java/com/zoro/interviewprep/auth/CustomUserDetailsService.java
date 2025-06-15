package com.zoro.interviewprep.auth;

import com.zoro.interviewprep.user.User;
import com.zoro.interviewprep.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepo; // ✅ DIP: depends on abstraction (interface)

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    // ✅ SRP: Only handles user loading for Spring Security login flow
}
