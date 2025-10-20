package com.snapp.expense_tracker.user.domin;

import com.snapp.expense_tracker.user.UserService;
import com.snapp.expense_tracker.user.model.CreateUserRequest;
import com.snapp.expense_tracker.user.model.LoginRequest;
import com.snapp.expense_tracker.user.model.LoginResponse;
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
        return null;
    }
}
