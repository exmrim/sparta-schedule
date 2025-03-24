package com.example.scheduleproject.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
public class Schedule {

    private Long id;
    private String title;
    private String contents;
    private String userId;
    private String userPw;
    private String userName;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public Schedule(String title, String contents, String userName, String userId, String userPw, LocalDateTime createDate, LocalDateTime updateDate) {
        this.title = title;
        this.contents = contents;
        this.userName = userName;
        this.userId = userId;
        this.userPw = userPw;
        this.createDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    public void update(String contents, String userName) {
        this.contents = contents;
        this.userName = userName;
    }

}
