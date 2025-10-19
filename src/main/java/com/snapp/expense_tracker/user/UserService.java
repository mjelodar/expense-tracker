package com.snapp.expense_tracker.user;

import com.snapp.expense_tracker.user.model.CreateUserRequest;

public interface UserService {
    void createUser(CreateUserRequest request);
}
