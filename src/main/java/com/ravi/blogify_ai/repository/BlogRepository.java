package com.ravi.blogify_ai.repository;

import com.ravi.blogify_ai.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BlogRepository extends JpaRepository<Blog, UUID> {
    List<Blog> findByAuthorId(UUID userId);
}
