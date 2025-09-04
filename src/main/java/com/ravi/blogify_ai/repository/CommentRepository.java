package com.ravi.blogify_ai.repository;

import com.ravi.blogify_ai.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
    List<Comment> findByBlogId(UUID blogId);
}
