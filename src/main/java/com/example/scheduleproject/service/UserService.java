package com.example.scheduleproject.service;

import com.example.scheduleproject.dto.UserRequestDto;
import com.example.scheduleproject.dto.UserResponseDto;

public interface UserService {

    UserResponseDto saveUser(UserRequestDto userRequestDto);

}
