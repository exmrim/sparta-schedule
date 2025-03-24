package com.example.scheduleproject.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ScheduleCreateDto {

    private Long id;
    private String title;
    private String contents;
    private String userId;
    private String userPw;
    private String userName;

    private LocalDate createDate;
    private LocalDate updateDate;

    public ScheduleCreateDto(Long id, String title, String contents, String userId, String userPw, String userName, LocalDate createDate, LocalDate updateDate){
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.createDate = LocalDate.now();
        this.updateDate = LocalDate.now();
    }

}
