package com.snapp.expense_tracker.user;

import com.snapp.expense_tracker.user.model.CreateUserRequest;
import com.snapp.expense_tracker.user.model.LoginRequest;
import com.snapp.expense_tracker.user.model.LoginResponse;

public interface UserService {
    void create(CreateUserRequest request);
    LoginResponse login(LoginRequest request);
}
