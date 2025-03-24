package com.example.scheduleproject.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
public class ScheduleRequestDto {

    private Long id;
    private String title;

    private String contents;
    private String userId;
    private String userPw;
    private String userName;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
