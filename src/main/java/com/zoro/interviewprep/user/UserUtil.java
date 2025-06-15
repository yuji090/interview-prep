package com.zoro.interviewprep.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtil {

    private final UserRepository userRepo;

    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName(); // fetched from JWT
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }
}
