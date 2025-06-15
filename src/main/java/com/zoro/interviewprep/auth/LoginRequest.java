package com.zoro.interviewprep.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;

    // ✅ SRP: Only used for capturing login credentials
}
