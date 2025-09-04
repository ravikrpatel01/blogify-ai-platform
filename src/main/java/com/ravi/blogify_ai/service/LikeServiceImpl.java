package com.ravi.blogify_ai.service;

import com.ravi.blogify_ai.entity.Blog;
import com.ravi.blogify_ai.entity.Like;
import com.ravi.blogify_ai.entity.User;
import com.ravi.blogify_ai.exceptions.BlogNotFoundException;
import com.ravi.blogify_ai.repository.BlogRepository;
import com.ravi.blogify_ai.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public String toggleLike(Blog blog, User user) {
        return likeRepository.findByUserIdAndBlogId(user.getId(), blog.getId())
                .map(like -> {
                    likeRepository.delete(like);
                    return "Unliked";
                })
                .orElseGet(() -> {
                    Like newLike = new Like();
                    newLike.setBlog(blog);
                    newLike.setUser(user);
                    likeRepository.save(newLike);
                    return "Liked";
                });
    }

    @Override
    public Long countLikes(UUID blogId) {
        return likeRepository.countByBlogId(blogId);
    }

    @Override
    public List<Like> getLikesByBlog(UUID blogId) {
        return likeRepository.findByBlogId(blogId);
    }
}
