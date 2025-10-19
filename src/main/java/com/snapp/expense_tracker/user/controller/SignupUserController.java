package com.snapp.expense_tracker.user.controller;

import com.snapp.expense_tracker.user.UserService;
import com.snapp.expense_tracker.user.model.CreateUserRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupUserController {
    private final UserService userService;

    public SignupUserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/auth/signup")
    public void handle(CreateUserRequest request){
        userService.createUser(request);
    }
}
