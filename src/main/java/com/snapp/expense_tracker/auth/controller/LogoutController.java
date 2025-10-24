package com.snapp.expense_tracker.auth.controller;

import com.snapp.expense_tracker.auth.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Authentication Module")
public class LogoutController {
    private final UserService userService;

    public LogoutController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping("/api/auth/logout")
    public void handle(){
        userService.logout();
    }
}
