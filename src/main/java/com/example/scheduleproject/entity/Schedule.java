package com.example.scheduleproject.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
public class Schedule {

    private Long id;
    private String title;
    private String contents;
    private String user_id;
    private String user_pw;
    private String user_name;

    private LocalDateTime create_date;
    private LocalDateTime update_date;

    public Schedule(String title, String contents, String user_name, String user_id, String user_pw, LocalDateTime create_date, LocalDateTime update_date) {
        this.title = title;
        this.contents = contents;
        this.user_name = user_name;
        this.user_id = user_id;
        this.user_pw = user_pw;
        this.create_date = LocalDateTime.now();
        this.update_date = LocalDateTime.now();
    }

    public void update(String contents, String user_name) {
        this.contents = contents;
        this.user_name = user_name;
    }

}
