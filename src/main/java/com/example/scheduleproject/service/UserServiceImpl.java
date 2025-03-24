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

    /**
     * 사용자 등록
     * @param userRequestDto
     * @return
     */
    @Override
    public UserResponseDto saveUser(UserRequestDto userRequestDto) {

        User user = new User(userRequestDto.getUserId(), userRequestDto.getUserPw(), userRequestDto.getUserName(), userRequestDto.getUserEmail(),
                userRequestDto.getAge(), userRequestDto.getJob(), userRequestDto.getCreateDate(), userRequestDto.getUpdateDate());

        return userRepository.saveUser(user);

    }


}
