package com.ravi.blogify_ai.service;

import com.ravi.blogify_ai.config.JwtProvider;
import com.ravi.blogify_ai.entity.User;
import com.ravi.blogify_ai.exceptions.UserNotFoundException;
import com.ravi.blogify_ai.repository.UserRepository;
import com.ravi.blogify_ai.request.UpdatePasswordRequest;
import com.ravi.blogify_ai.request.UpdateUserRequest;
import com.ravi.blogify_ai.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserByEmail(String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UserNotFoundException("User not found with email: " + email);
        }
        return user;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUser(UUID id) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found with UUID: " + id);
        }
        return userOptional.get();
    }

    @Override
    public User findUserFromJwtToken(String jwt) throws UserNotFoundException {
        String email = JwtProvider.getEmailFromJwtToken(jwt);

        return findUserByEmail(email);
    }

    @Override
    public User updateUserDetails(UUID userId, UpdateUserRequest user) throws UserNotFoundException {
        User existingUser = findUser(userId);
        if (user.getFullName() != null && user.getFullName().equals(existingUser.getFullName())) {
            existingUser.setFullName(user.getFullName());
        }

        if (user.getEmail() != null && user.getEmail().equals(existingUser.getEmail())) {
            existingUser.setEmail(user.getEmail());
        }
        return userRepository.save(existingUser);
    }

    @Override
    public ResponseMessage updatePassword(UUID userId, UpdatePasswordRequest password) throws UserNotFoundException {
        User user = findUser(userId);
        user.setPassword(password.getPassword());
        userRepository.save(user);

        ResponseMessage response = new ResponseMessage();
        response.setMessage("Password updated successfully!");
        response.setStatus(true);
        return response;
    }
}
