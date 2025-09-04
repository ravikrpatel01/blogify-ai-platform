package com.ravi.blogify_ai.repository;

import com.ravi.blogify_ai.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LikeRepository extends JpaRepository<Like, UUID> {
    Optional<Like> findByUserIdAndBlogId(UUID userId, UUID blogId);
    Long countByBlogId(UUID blogId);
    List<Like> findByBlogId(UUID blogId);
}
