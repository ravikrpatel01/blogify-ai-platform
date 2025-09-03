package com.ravi.blogify_ai.service;

import com.ravi.blogify_ai.entity.User;
import com.ravi.blogify_ai.exceptions.UserNotFoundException;
import com.ravi.blogify_ai.request.UserRequest;
import org.springframework.stereotype.Service;

public interface UserService {
    User createUser(UserRequest user);
    public User findUserByEmail(String email) throws UserNotFoundException;
}
