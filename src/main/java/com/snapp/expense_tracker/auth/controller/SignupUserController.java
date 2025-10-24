package com.snapp.expense_tracker.auth.controller;

import com.snapp.expense_tracker.auth.model.CreateUserRequest;
import com.snapp.expense_tracker.auth.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Authentication Module")
public class SignupUserController {
    private final UserService userService;

    public SignupUserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/auth/signup")
    public void handle(@RequestBody CreateUserRequest request){
        userService.create(request);
    }
}
