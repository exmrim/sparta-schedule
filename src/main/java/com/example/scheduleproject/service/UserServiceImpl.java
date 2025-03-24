package com.example.scheduleproject.service;

import com.example.scheduleproject.dto.UserRequestDto;
import com.example.scheduleproject.dto.UserResponseDto;
import com.example.scheduleproject.entity.User;
import com.example.scheduleproject.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto saveUser(UserRequestDto userRequestDto) {

        User user = new User(userRequestDto.getUser_id(), userRequestDto.getUser_pw(), userRequestDto.getUser_name(), userRequestDto.getUser_email(),
                userRequestDto.getAge(), userRequestDto.getJob(), userRequestDto.getCreate_date(), userRequestDto.getUpdate_date());

        return userRepository.saveUser(user);

    }


}
