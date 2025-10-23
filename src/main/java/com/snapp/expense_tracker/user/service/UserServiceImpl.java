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
class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
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

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.username()).orElseThrow(BadCredentialsException::new);
        if (!passwordEncoder.matches(request.password(), user.getPassword()))
            throw new BadCredentialsException();
        return new LoginResponse(JWTUtil.generateToken(user.getUsername(), user.getId(), 10000000000L));
    }
}
