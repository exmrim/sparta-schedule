package com.example.scheduleproject.dto;
import com.example.scheduleproject.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {

    private Long id;
    private String title;
    private String contents;
    private String userId;
    private String userPw;
    private String userName;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.userId = schedule.getUserId();
        this.userPw = schedule.getUserPw();
        this.userName = schedule.getUserName();
        this.createDate = schedule.getCreateDate();
        this.updateDate = schedule.getUpdateDate();

    }
}
