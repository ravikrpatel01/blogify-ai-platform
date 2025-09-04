package com.ravi.blogify_ai.controller;

import com.ravi.blogify_ai.entity.Blog;
import com.ravi.blogify_ai.entity.User;
import com.ravi.blogify_ai.exceptions.BlogNotFoundException;
import com.ravi.blogify_ai.exceptions.UserNotFoundException;
import com.ravi.blogify_ai.request.BlogRequest;
import com.ravi.blogify_ai.service.BlogService;
import com.ravi.blogify_ai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Blog> createBlog(
            @ModelAttribute BlogRequest blog,
            @RequestHeader("Authorization") String jwt
    ) throws UserNotFoundException {
        User user = userService.findUserFromJwtToken(jwt);
        Blog createdBlog = blogService.createBlog(blog, user);

        return new ResponseEntity<>(createdBlog, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Blog>> findAllBlogs() {
        List<Blog> blogs = blogService.findAllBlogs();

        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> findBlog(@PathVariable UUID id) throws BlogNotFoundException {
        Blog blog = blogService.findBlog(id);
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Blog>> findBlogsByUser(@PathVariable UUID userId) {
        List<Blog> blogs = blogService.findBlogsByUser(userId);
        return ResponseEntity.ok(blogs);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Blog> updateBlog(
            @PathVariable UUID id,
            @RequestBody BlogRequest blog
    ) throws BlogNotFoundException {
        Blog updatedBlog = blogService.updateBlog(id, blog);
        return new ResponseEntity<>(updatedBlog, HttpStatus.OK);
    }
}
