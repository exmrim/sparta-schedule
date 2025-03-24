package com.example.scheduleproject.dto;

import com.example.scheduleproject.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserResponseDto {

    private String id;
    private String user_id;
    private String user_pw;
    private String user_name;
    private String user_email;
    private int age;
    private String job;

    private LocalDateTime create_date;
    private LocalDateTime update_date;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.user_id = user.getUser_id();
        this.user_pw = user.getUser_pw();
        this.user_name = user.getUser_name();
        this.user_email = user.getUser_email();
        this.age = user.getAge();
        this.job = user.getJob();
        this.create_date = user.getCreate_date();
        this.update_date = user.getUpdate_date();
    }
}
