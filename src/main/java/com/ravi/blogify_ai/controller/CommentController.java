package com.ravi.blogify_ai.controller;

import com.ravi.blogify_ai.entity.Blog;
import com.ravi.blogify_ai.entity.Comment;
import com.ravi.blogify_ai.entity.User;
import com.ravi.blogify_ai.exceptions.BlogNotFoundException;
import com.ravi.blogify_ai.exceptions.CommentNotFoundException;
import com.ravi.blogify_ai.exceptions.UserNotFoundException;
import com.ravi.blogify_ai.request.CommentRequest;
import com.ravi.blogify_ai.response.ResponseMessage;
import com.ravi.blogify_ai.service.BlogService;
import com.ravi.blogify_ai.service.CommentService;
import com.ravi.blogify_ai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;

    @PostMapping("/{blogId}")
    public ResponseEntity<Comment> addComment(
            @RequestBody CommentRequest comment,
            @PathVariable UUID blogId,
            @RequestHeader("Authorization") String jwt
    ) throws UserNotFoundException, BlogNotFoundException {
        User user = userService.findUserFromJwtToken(jwt);
        Blog blog = blogService.findBlog(blogId);

        Comment addedComment = commentService.addComment(comment, user, blog);
        return new ResponseEntity<>(addedComment, HttpStatus.CREATED);
    }

    @GetMapping("/blog/{blogId}")
    public ResponseEntity<List<Comment>> findCommentsByBlog(
            @PathVariable UUID blogId
    ) {
        List<Comment> comments = commentService.findCommentsByBlogId(blogId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseMessage> updateComment(
            @PathVariable UUID id,
            @RequestBody CommentRequest comment
    ) throws CommentNotFoundException {
        Comment updatedComment = commentService.updateComment(id, comment);
        ResponseMessage response = new ResponseMessage();
        response.setMessage("Comment updated successfully!");
        response.setStatus(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
