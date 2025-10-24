package com.snapp.expense_tracker.auth.controller;

import com.snapp.expense_tracker.auth.model.LoginRequest;
import com.snapp.expense_tracker.auth.model.LoginResponse;
import com.snapp.expense_tracker.auth.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Authentication Module")
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
