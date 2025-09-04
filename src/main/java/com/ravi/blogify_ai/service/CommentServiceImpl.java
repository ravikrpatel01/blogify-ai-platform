package com.ravi.blogify_ai.service;

import com.ravi.blogify_ai.entity.Blog;
import com.ravi.blogify_ai.entity.Comment;
import com.ravi.blogify_ai.entity.User;
import com.ravi.blogify_ai.exceptions.CommentNotFoundException;
import com.ravi.blogify_ai.repository.CommentRepository;
import com.ravi.blogify_ai.request.CommentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment addComment(CommentRequest comment, User user, Blog blog) {
        Comment newComment = new Comment();
        newComment.setContent(comment.getContent());
        newComment.setUser(user);
        newComment.setCreatedAt(LocalDateTime.now());
        newComment.setUpdatedAt(LocalDateTime.now());
        newComment.setBlog(blog);

        return commentRepository.save(newComment);
    }

    @Override
    public List<Comment> findAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findComment(UUID id) throws CommentNotFoundException {
        Optional<Comment> commentOptional = commentRepository.findById(id);

        if (commentOptional.isEmpty()) {
            throw new CommentNotFoundException("Comment not found with UUID: " + id);
        }
        return commentOptional.get();
    }

    @Override
    public List<Comment> findCommentsByBlogId(UUID blogId) {
        return commentRepository.findByBlogId(blogId);
    }

    @Override
    public Comment updateComment(UUID id, CommentRequest comment) throws CommentNotFoundException {
        Comment existingComment = findComment(id);
        existingComment.setContent(comment.getContent());

        return commentRepository.save(existingComment);
    }
}
