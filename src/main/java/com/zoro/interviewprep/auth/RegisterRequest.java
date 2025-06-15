package com.zoro.interviewprep.auth;

import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;

    // ✅ SRP: Only used for capturing registration data
}
