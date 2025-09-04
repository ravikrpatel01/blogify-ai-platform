package com.ravi.blogify_ai.response;

import com.ravi.blogify_ai.dto.UserDTO;
import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String message;
    private UserDTO user;
}
