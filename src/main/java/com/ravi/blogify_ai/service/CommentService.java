package com.ravi.blogify_ai.service;

import com.ravi.blogify_ai.entity.Blog;
import com.ravi.blogify_ai.entity.Comment;
import com.ravi.blogify_ai.entity.User;
import com.ravi.blogify_ai.exceptions.CommentNotFoundException;
import com.ravi.blogify_ai.request.CommentRequest;

import java.util.List;
import java.util.UUID;

public interface CommentService {
    public Comment addComment(CommentRequest comment, User user, Blog blog);
    public List<Comment> findAllComments();
    public Comment findComment(UUID id) throws CommentNotFoundException;
    public List<Comment> findCommentsByBlogId(UUID blogId);
    Comment updateComment(UUID id, CommentRequest comment) throws CommentNotFoundException;
}
