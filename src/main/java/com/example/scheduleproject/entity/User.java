package com.example.scheduleproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class User {

    private String id;
    private String userId;
    private String userPw;
    private String userName;
    private String userEmail;
    private int age;
    private String job;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public User(
            String userId, String userPw, String userName, String userEmail,
            int age, String job, LocalDateTime createDate, LocalDateTime updateDate)
    {
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.userEmail = userEmail;
        this.age = age;
        this.job = job;
        this.createDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

}
