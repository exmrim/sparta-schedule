package com.example.scheduleproject.dto;

import com.example.scheduleproject.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserResponseDto {

    private String id;
    private String userId;
    private String userPw;
    private String userName;
    private String userEmail;
    private int age;
    private String job;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.userId = user.getUserId();
        this.userPw = user.getUserPw();
        this.userName = user.getUserName();
        this.userEmail = user.getUserEmail();
        this.age = user.getAge();
        this.job = user.getJob();
        this.createDate = user.getCreateDate();
        this.updateDate = user.getUpdateDate();
    }
}
