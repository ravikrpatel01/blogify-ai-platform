package com.ravi.blogify_ai.service;

import com.ravi.blogify_ai.entity.User;
import com.ravi.blogify_ai.exceptions.UserNotFoundException;
import com.ravi.blogify_ai.request.UpdatePasswordRequest;
import com.ravi.blogify_ai.request.UpdateUserRequest;
import com.ravi.blogify_ai.request.UserRequest;
import com.ravi.blogify_ai.response.ResponseMessage;

import java.util.List;
import java.util.UUID;

public interface UserService {
    public User findUserByEmail(String email) throws UserNotFoundException;
    public List<User> findAllUsers();
    public User findUser(UUID id) throws UserNotFoundException;
    public User findUserFromJwtToken(String jwt) throws UserNotFoundException;
    public User updateUserDetails(UUID userId, UpdateUserRequest user) throws UserNotFoundException;
    public ResponseMessage updatePassword(UUID userId, UpdatePasswordRequest password) throws UserNotFoundException;
}
