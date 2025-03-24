package com.example.scheduleproject.service;

import com.example.scheduleproject.dto.*;
import com.example.scheduleproject.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleService {

    ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto);

    List<ScheduleResponseDto> findAllSchedules();

    ScheduleResponseDto findScheduleById(Long id, Long inputId);

    ScheduleResponseDto findScheduleByUser(String user);

    ScheduleResponseDto updateSchedule(Long id, String contents, String userName, String user_pPw);

    void deleteSchedule(Long id, String userPw);

    ScheduleResponseDto checkPw(Long id);
}
