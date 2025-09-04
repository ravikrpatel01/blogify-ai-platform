package com.ravi.blogify_ai.controller;

import com.ravi.blogify_ai.entity.User;
import com.ravi.blogify_ai.exceptions.UserNotFoundException;
import com.ravi.blogify_ai.request.UpdatePasswordRequest;
import com.ravi.blogify_ai.request.UpdateUserRequest;
import com.ravi.blogify_ai.response.ResponseMessage;
import com.ravi.blogify_ai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> users = userService.findAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUser(@PathVariable UUID id) throws UserNotFoundException {
        User user = userService.findUser(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateUserDetails(
            @PathVariable UUID userId,
            @RequestBody UpdateUserRequest user
    ) throws UserNotFoundException {
        User updatedUser = userService.updateUserDetails(userId, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PutMapping("/update-password/{userId}")
    public ResponseEntity<ResponseMessage> updatePassword(
            @PathVariable UUID userId,
            @RequestBody UpdatePasswordRequest password
    ) throws UserNotFoundException {
        ResponseMessage responseMessage = userService.updatePassword(userId, password);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
