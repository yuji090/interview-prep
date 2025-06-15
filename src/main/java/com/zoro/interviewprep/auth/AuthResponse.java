package com.zoro.interviewprep.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;

    // ✅ SRP: Only used for sending JWT token back to the client
}
