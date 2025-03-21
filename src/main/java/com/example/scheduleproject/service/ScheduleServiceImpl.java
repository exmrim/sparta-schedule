package com.example.scheduleproject.service;

import com.example.scheduleproject.dto.ScheduleRequestDto;
import com.example.scheduleproject.dto.ScheduleResponseDto;
import com.example.scheduleproject.entity.Schedule;
import com.example.scheduleproject.repository.ScheduleRepository;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto) {

        Schedule schedule = new Schedule(scheduleRequestDto.getTitle(), scheduleRequestDto.getContents(), scheduleRequestDto.getUser_name(), scheduleRequestDto.getUser_id(), scheduleRequestDto.getUser_pw(), scheduleRequestDto.getCreate_date(), scheduleRequestDto.getUpdate_date());

        return scheduleRepository.saveSchedule(schedule);

    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        return scheduleRepository.findAllSchedules();
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);
        return new ScheduleResponseDto(schedule);
    }

    @Override
    public ScheduleResponseDto checkPw(Long id) {
        Schedule schedule = scheduleRepository.checkPw(id);
        return new ScheduleResponseDto(schedule);
    }

    @Transactional
    @Override
    public ScheduleResponseDto updateSchedule(Long id, String contents, String user_name) {

        String result = checkPw(id).getUser_pw();

        if(result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Password not found.");
        }

        if(contents == null || user_name == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The contents and name are required values.");
        }

        int updateRow = scheduleRepository.updateSchedule(id, contents, user_name);
        if(updateRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data has been modified.");
        }

        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        return new ScheduleResponseDto(schedule);
    }

    public void deleteSchedule(Long id) {

        String result = checkPw(id).getUser_pw();

        if(result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Password not found.");
        }

        int deleteRow = scheduleRepository.deleteSchedule(id);

        if(deleteRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule id not found. id = " + id);
        }
    }

}
