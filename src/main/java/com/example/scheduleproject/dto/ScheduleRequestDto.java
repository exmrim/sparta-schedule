package com.example.scheduleproject.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
public class ScheduleRequestDto {

    private Long id;
    private String title;
    private String contents;
    private String user_id;
    private String user_pw;
    private String user_name;
    private LocalDateTime create_date;
    private LocalDateTime update_date;
}
