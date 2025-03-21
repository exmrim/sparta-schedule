package com.example.scheduleproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class User {

    private Long id;
    private String user_id;
    private String user_pw;
    private String user_name;
    private String user_email;
    private int age;
    private String job;

    private LocalDateTime create_date;
    private LocalDateTime update_date;

    public User(
            Long id, String user_id, String user_pw, String user_name, String user_email,
            int age, String job, LocalDateTime create_date, LocalDateTime update_date)
    {
        this.id = id;
        this.user_id = user_id;
        this.user_pw = user_pw;
        this.user_name = user_name;
        this.user_email = user_email;
        this.age = age;
        this.job = job;
        this.create_date = LocalDateTime.now();
        this.update_date = LocalDateTime.now();
    }

}
