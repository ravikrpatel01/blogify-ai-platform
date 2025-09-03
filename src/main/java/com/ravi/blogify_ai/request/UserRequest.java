package com.ravi.blogify_ai.request;

import lombok.Data;

@Data
public class UserRequest {
    private String fullName;
    private String email;
    private String password;
}
