package com.ravi.blogify_ai.controller;

import com.ravi.blogify_ai.entity.Blog;
import com.ravi.blogify_ai.entity.Like;
import com.ravi.blogify_ai.entity.User;
import com.ravi.blogify_ai.exceptions.BlogNotFoundException;
import com.ravi.blogify_ai.exceptions.UserNotFoundException;
import com.ravi.blogify_ai.service.BlogService;
import com.ravi.blogify_ai.service.LikeService;
import com.ravi.blogify_ai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/likes")
public class LikeController {
    @Autowired
    private LikeService likeService;

    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;

    @PostMapping("/{blogId}")
    public ResponseEntity<String> toggleLike(
            @PathVariable UUID blogId,
            @RequestHeader("Authorization") String jwt
    ) throws UserNotFoundException, BlogNotFoundException {
        User user = userService.findUserFromJwtToken(jwt);
        Blog blog = blogService.findBlog(blogId);

        String result = likeService.toggleLike(blog, user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/count/{blogId}")
    public ResponseEntity<Long> countLikes(@PathVariable UUID blogId) {
        return ResponseEntity.ok(likeService.countLikes(blogId));
    }

    @GetMapping("/blog/{blogId}")
    public ResponseEntity<List<Like>> getLikesByBlog(@PathVariable UUID blogId) {
        return ResponseEntity.ok(likeService.getLikesByBlog(blogId));
    }
}
