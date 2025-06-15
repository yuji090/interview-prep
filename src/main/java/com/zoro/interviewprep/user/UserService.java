package com.zoro.interviewprep.user;

import com.zoro.interviewprep.user.dto.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDTO getCurrentUser() {
        // Step 1: Get logged-in username (email in your case)
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        // Step 2: Fetch User from DB using email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Step 3: Convert to DTO
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());

        return dto;
    }
}
