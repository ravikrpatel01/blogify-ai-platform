package com.ravi.blogify_ai.service;

import com.ravi.blogify_ai.entity.Blog;
import com.ravi.blogify_ai.entity.User;
import com.ravi.blogify_ai.exceptions.BlogNotFoundException;
import com.ravi.blogify_ai.request.BlogRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface BlogService {
    public Blog createBlog(BlogRequest blog, User user);
    public List<Blog> findAllBlogs();
    public Blog findBlog(UUID id) throws BlogNotFoundException ;
    public List<Blog> findBlogsByUser(UUID userId);

    Blog updateBlog(UUID id, BlogRequest blog) throws BlogNotFoundException;
}
