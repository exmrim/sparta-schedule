package com.example.scheduleproject.repository;

import com.example.scheduleproject.dto.ScheduleResponseDto;
import com.example.scheduleproject.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {

    ScheduleResponseDto saveSchedule(Schedule schedule);

    List<ScheduleResponseDto> findAllSchedules();

    Schedule findScheduleByIdOrElseThrow(Long id);

    Schedule findScheduleByUserOrElseThrow(String user);

    Schedule checkPw(Long id);

    int updateSchedule(Long id, String contents, String userName);

    int deleteSchedule(Long id);
}
