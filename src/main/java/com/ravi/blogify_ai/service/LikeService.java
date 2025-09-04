package com.ravi.blogify_ai.service;

import com.ravi.blogify_ai.entity.Blog;
import com.ravi.blogify_ai.entity.Like;
import com.ravi.blogify_ai.entity.User;

import java.util.List;
import java.util.UUID;

public interface LikeService {
    public String toggleLike(Blog blog, User user);
    public Long countLikes(UUID blogId);
    public List<Like> getLikesByBlog(UUID blogId);
}
