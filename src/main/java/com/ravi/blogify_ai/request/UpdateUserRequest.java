package com.ravi.blogify_ai.request;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String fullName;
    private String email;
}
