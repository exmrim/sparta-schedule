package com.example.scheduleproject.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserRequestDto {

    private String id;
    private String user_id;
    private String user_pw;
    private String user_name;
    private String user_email;
    private int age;
    private String job;
    private LocalDateTime create_date;
    private LocalDateTime update_date;
}
