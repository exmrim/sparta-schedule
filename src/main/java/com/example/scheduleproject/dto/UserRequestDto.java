package com.example.scheduleproject.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserRequestDto {

    private String id;
    private String userId;
    private String userPw;
    private String userName;
    private String userEmail;
    private int age;
    private String job;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
