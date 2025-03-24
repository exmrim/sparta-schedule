package com.example.scheduleproject.dto;

import com.example.scheduleproject.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter

public class ScheduleCreateDto {

    private Long id;
    private String title;
    private String contents;
    private String user_id;
    private String user_pw;
    private String user_name;

    private LocalDateTime create_date;
    private LocalDateTime update_date;

    public ScheduleCreateDto(Long id, String title, String contents, String user_id, String user_pw, String user_name, LocalDateTime create_date, LocalDateTime update_date){
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.user_id = user_id;
        this.user_pw = user_pw;
        this.user_name = user_name;
        this.create_date = LocalDateTime.now();
        this.update_date = LocalDateTime.now();
    }

}
