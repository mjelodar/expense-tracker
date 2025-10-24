package com.snapp.expense_tracker.auth.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(String firstName,
                                String surname,
                                @NotBlank String username,
                                @Size(min = 8, message = "password must be at least 8 character") String password) {
}
