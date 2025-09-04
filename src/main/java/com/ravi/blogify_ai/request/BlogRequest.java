package com.ravi.blogify_ai.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BlogRequest {
    private String title;
    private String content;
    private MultipartFile image;
}
