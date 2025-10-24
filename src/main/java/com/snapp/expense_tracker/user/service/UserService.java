package com.snapp.expense_tracker.user.service;

import com.snapp.expense_tracker.common.util.SecurityUtil;
import com.snapp.expense_tracker.user.domin.User;
import com.snapp.expense_tracker.user.model.CreateUserRequest;
import com.snapp.expense_tracker.user.model.LoginRequest;
import com.snapp.expense_tracker.user.model.LoginResponse;
import com.snapp.expense_tracker.user.model.RefreshRequest;
import com.snapp.expense_tracker.user.repository.BadCredentialsException;
import com.snapp.expense_tracker.user.repository.UserRepository;
import com.snapp.expense_tracker.user.repository.UsernameAlreadyExistedException;
import com.snapp.expense_tracker.user.util.JWTUtil;
import com.snapp.expense_tracker.user.util.RedisUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RedisUtil redisUtil;
    private final JWTUtil jwtUtil;

    @Value("${app.access-token-ttl}")
    private Long accessTokenTTL;

    @Value("${app.refresh-token-ttl}")
    private Long refreshTokenTTL;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       RedisUtil redisUtil,
                       JWTUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.redisUtil = redisUtil;
        this.jwtUtil = jwtUtil;
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

        String accessToken = createAccessToken(user);
        String refreshToken = createRefreshToken(user);

        return new LoginResponse(accessToken, refreshToken, user.getId());
    }

    public void logout(){
        //TODO handle current valid accessToken
        redisUtil.deleteRefreshToken(SecurityUtil.getUserId());
    }

    public LoginResponse refreshToken(RefreshRequest request) {
        User user = userRepository.findById(request.userId()).orElseThrow(BadCredentialsException::new);
        redisUtil.validateRefreshToken(SecurityUtil.getUserId(), request.refreshToken());

        String accessToken = createAccessToken(user);
        String newRefreshToken = createRefreshToken(user);

        return new LoginResponse(accessToken, newRefreshToken, user.getId());
    }

    private String createAccessToken(User user){
        return jwtUtil.generateToken(user.getUsername(), user.getId(), accessTokenTTL);
    }

    private String createRefreshToken(User user){
        String refreshToken = UUID.randomUUID().toString().replace("-", "");
        redisUtil.saveRefreshToken(user.getId(), refreshToken, refreshTokenTTL);
        return refreshToken;
    }
}
