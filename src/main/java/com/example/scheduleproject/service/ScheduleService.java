package com.example.scheduleproject.service;

import com.example.scheduleproject.dto.*;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleService {

    ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto);

    List<ScheduleResponseDto> findAllSchedules();

    ScheduleResponseDto findScheduleById(Long id);

    ScheduleResponseDto findScheduleByUser(String user);

    ScheduleResponseDto checkPw(Long id);

    ScheduleResponseDto updateSchedule(Long id, String contents, String user_name);

    void deleteSchedule(Long id);
}
