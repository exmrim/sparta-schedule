package com.example.scheduleproject.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
public class ScheduleRequestDto {

    //변수명 camelCase로 수정하기******************************
    private Long id;
    private String title;
    private String contents;
    private String user_id;
    private String user_pw; // 필요없는 부분
    private String user_name; // 필요없는 부분
    private LocalDateTime create_date;
    private LocalDateTime update_date;
}
