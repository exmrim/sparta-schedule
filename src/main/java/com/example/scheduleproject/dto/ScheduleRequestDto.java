package com.example.scheduleproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.websocket.OnMessage;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


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
