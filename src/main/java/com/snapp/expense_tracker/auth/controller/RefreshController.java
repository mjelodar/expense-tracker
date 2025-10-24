package com.snapp.expense_tracker.auth.controller;

import com.snapp.expense_tracker.auth.model.LoginResponse;
import com.snapp.expense_tracker.auth.model.RefreshRequest;
import com.snapp.expense_tracker.auth.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Authentication Module")
public class RefreshController {
    private final UserService userService;

    public RefreshController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/auth/refresh")
    public LoginResponse refresh(@RequestBody RefreshRequest request) {
        return userService.refreshToken(request);
    }

}
