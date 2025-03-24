package com.example.scheduleproject.repository;

import com.example.scheduleproject.dto.UserResponseDto;
import com.example.scheduleproject.entity.User;

import java.util.Optional;

public interface UserRepository {

    UserResponseDto saveUser(User user);

    Optional<User> findByIdOrElseThrow(String userId);
}
