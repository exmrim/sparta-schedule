package com.example.scheduleproject.dto;
import com.example.scheduleproject.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {

    private Long id;
    private String title;
    private String contents;
    private String user_id;
    private String user_pw;
    private String user_name;

    private LocalDateTime create_date;
    private LocalDateTime update_date;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.user_id = schedule.getUser_id();
        this.user_pw = schedule.getUser_pw();
        this.user_name = schedule.getUser_name();
        this.create_date = schedule.getCreate_date();
        this.update_date = schedule.getUpdate_date();

    }
}
