package com.ravi.blogify_ai.service;

import com.ravi.blogify_ai.entity.Blog;
import com.ravi.blogify_ai.entity.User;
import com.ravi.blogify_ai.exceptions.BlogNotFoundException;
import com.ravi.blogify_ai.repository.BlogRepository;
import com.ravi.blogify_ai.request.BlogRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog createBlog(BlogRequest blog, User user) {
        Blog newBlog = new Blog();
        newBlog.setTitle(blog.getTitle());
        newBlog.setContent(blog.getContent());
        newBlog.setAuthor(user);

        return blogRepository.save(newBlog);
    }

    @Override
    public List<Blog> findAllBlogs() {
        return blogRepository.findAll();
    }

    @Override
    public Blog findBlog(UUID id) throws BlogNotFoundException {
        Optional<Blog> blogOptional = blogRepository.findById(id);

        if (blogOptional.isEmpty()) {
            throw new BlogNotFoundException("Blog not found with UUID: " + id);
        }
        return blogOptional.get();
    }

    @Override
    public List<Blog> findBlogsByUser(UUID userId) {
        return blogRepository.findByAuthorId(userId);
    }

    @Override
    public Blog updateBlog(UUID id, BlogRequest blog) throws BlogNotFoundException {
        Blog existingBlog = findBlog(id);
        if (blog.getTitle() != null) {
            existingBlog.setTitle(blog.getTitle());
        }
        if (blog.getContent() != null) {
            existingBlog.setContent(blog.getContent());
        }
        return blogRepository.save(existingBlog);
    }
}
