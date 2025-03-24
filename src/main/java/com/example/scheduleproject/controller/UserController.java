package com.example.scheduleproject.controller;

import com.example.scheduleproject.dto.UserRequestDto;
import com.example.scheduleproject.dto.UserResponseDto;
import com.example.scheduleproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 사용자 생성 API
     * @param
     * @return
     */
    @PostMapping
    public ResponseEntity<UserResponseDto> addUser(@RequestBody UserRequestDto userRequestDto) {
        return new ResponseEntity<>(userService.saveUser(userRequestDto), HttpStatus.CREATED);
    }


}
