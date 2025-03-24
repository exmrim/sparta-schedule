package com.example.scheduleproject.repository;

import com.example.scheduleproject.dto.ScheduleCreateDto;
import com.example.scheduleproject.dto.ScheduleResponseDto;
import com.example.scheduleproject.dto.UserResponseDto;
import com.example.scheduleproject.entity.Schedule;
import com.example.scheduleproject.entity.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    ScheduleResponseDto saveSchedule(Schedule schedule);

    List<ScheduleResponseDto> findAllSchedules();

    //Optional<Schedule> findScheduleById(Long id);

    Schedule findScheduleByIdOrElseThrow(Long id);

    Schedule findScheduleByUserOrElseThrow(String user);

    Schedule checkPw(Long id);

    int updateSchedule(Long id, String contents, String user_name);

    int deleteSchedule(Long id);
}
