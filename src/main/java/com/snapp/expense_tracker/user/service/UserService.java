package com.snapp.expense_tracker.user.service;

import com.snapp.expense_tracker.user.domin.User;
import com.snapp.expense_tracker.user.model.CreateUserRequest;
import com.snapp.expense_tracker.user.model.LoginRequest;
import com.snapp.expense_tracker.user.model.LoginResponse;
import com.snapp.expense_tracker.user.repository.BadCredentialsException;
import com.snapp.expense_tracker.user.repository.UserRepository;
import com.snapp.expense_tracker.user.repository.UsernameAlreadyExistedException;
import com.snapp.expense_tracker.user.util.JWTUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       RefreshTokenService refreshTokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.refreshTokenService = refreshTokenService;
    }

    public void create(CreateUserRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new UsernameAlreadyExistedException();
        }

        User user = new User();
        user.setUsername(request.username());
        user.setFirstName(request.firstName());
        user.setSurname(request.surname());
        user.setPassword(passwordEncoder.encode(request.password()));

        userRepository.save(user);
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.username()).orElseThrow(BadCredentialsException::new);
        if (!passwordEncoder.matches(request.password(), user.getPassword()))
            throw new BadCredentialsException();
        long accessTokenTtl = 15 * 60 * 1000L; // 15 minutes
        long refreshTokenTtl = 7 * 24 * 60 * 60 * 1000L; // 7 days

        String accessToken = JWTUtil.generateToken(user.getUsername(), user.getId(), accessTokenTtl);
        String refreshToken = JWTUtil.generateToken(user.getUsername(), user.getId(), refreshTokenTtl);

        refreshTokenService.saveRefreshToken(user.getId(), refreshToken, refreshTokenTtl);

        return new LoginResponse(accessToken, refreshToken);
    }
}
