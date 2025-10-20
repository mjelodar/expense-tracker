package com.snapp.expense_tracker.user.controller;

import com.snapp.expense_tracker.user.UserService;
import com.snapp.expense_tracker.user.model.LoginRequest;
import com.snapp.expense_tracker.user.model.LoginResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/auth/login")
    public LoginResponse handle(@RequestBody LoginRequest request){
        return userService.login(request);
    }
}
