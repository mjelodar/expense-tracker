package com.snapp.expense_tracker.user.controller;

import com.snapp.expense_tracker.common.util.SecurityUtil;
import com.snapp.expense_tracker.user.model.LoginResponse;
import com.snapp.expense_tracker.user.model.RefreshRequest;
import com.snapp.expense_tracker.user.repository.BadCredentialsException;
import com.snapp.expense_tracker.user.service.RefreshTokenService;
import com.snapp.expense_tracker.user.util.JWTUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class RefreshController {
    private final RefreshTokenService refreshTokenService;

    public RefreshController(RefreshTokenService refreshTokenService) {
        this.refreshTokenService = refreshTokenService;
    }

//    @PostMapping("/api/auth/refresh")
//    public LoginResponse refresh(@RequestBody RefreshRequest request) {
//        String refreshToken = request.refreshToken();
//
//        if (refreshTokenService.validateRefreshToken(SecurityUtil.getUserId(), refreshToken)) {
//            throw new BadCredentialsException();
//        }
//
//        Long userId = JWTUtil.extractUserId(refreshToken);
//        String username = JWTUtil.extractUsername(refreshToken);
//
//        if (refreshTokenService.validateRefreshToken(userId, refreshToken)) {
//            throw new BadCredentialsException("Refresh token expired or invalid");
//        }
//
//        String newAccessToken = JWTUtil.generateToken(username, userId, 15 * 60 * 1000L);
//
//        return new LoginResponse(newAccessToken, refreshToken);
//    }

}
