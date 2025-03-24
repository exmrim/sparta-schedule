package com.example.scheduleproject.service;

import com.example.scheduleproject.dto.*;
import com.example.scheduleproject.entity.Schedule;
import com.example.scheduleproject.repository.ScheduleRepository;
import exception.InvalidHandler;
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

        invalidException(schedule);

        return scheduleRepository.saveSchedule(schedule);

    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        return scheduleRepository.findAllSchedules();
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id, Long inputId) {

        //삭제된 정보
        checkScheduleException(id, inputId);

        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);
        return new ScheduleResponseDto(schedule);
    }

    @Override
    public ScheduleResponseDto findScheduleByUser(String user) {

        //잘못된 정보
        checkUserException(user);

        Schedule schedule = scheduleRepository.findScheduleByUserOrElseThrow(user);
        return new ScheduleResponseDto(schedule);
    }

    @Transactional
    @Override
    public ScheduleResponseDto updateSchedule(Long id, String contents, String user_name, String user_pw) {

        checkPwException(id, user_pw);

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

    @Override
    public void deleteSchedule(Long id, String user_pw) {

        checkPwException(id, user_pw);

        int deleteRow = scheduleRepository.deleteSchedule(id);

        if(deleteRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule id not found. id = " + id);
        }
    }

    @Override
    public ScheduleResponseDto checkPw(Long id) {
        Schedule schedule = scheduleRepository.checkPw(id);
        return new ScheduleResponseDto(schedule);
    }

    public boolean checkUserException(String user) {
        String userId = findScheduleByUser(user).getUser_id();

        if(userId == null) {
            throw new InvalidHandler("UserNull","User not found.");
        }
        return true;
    }

    public boolean checkPwException(Long id, String inputPw) {
        String userPw = checkPw(id).getUser_pw();

        if(userPw == null) {
            throw new InvalidHandler("UserPasswordNull","User Password not found.");
        } else if(!userPw.equals(inputPw)) {
            throw new InvalidHandler("UserPasswordMatch","Password does not match.");
        }
        return true;
    }

    public boolean checkScheduleException(Long id, Long inputId) {
        Long num = findAllSchedules().get(0).getId();

        if(num == null) {
            throw new InvalidHandler("ScheduleNull","Schedule not found.");
        } else if(num > inputId) {
            throw new InvalidHandler("ScheduleDelete","Schedule is deleted.");
        }
        return true;
    }

    public boolean invalidException(Schedule schedule) {

        String contents = schedule.getContents();

        if(contents == null) {
            throw new InvalidHandler("ContentsNull","Contents are null.");
        } else if(contents.equals("")) {
            throw new InvalidHandler("ContentsEmpty","Contents are required.");
        } else if(contents.length() > 20) {
            throw new InvalidHandler("ContentsNull","Contents are too long.");
        }
        return true;
    }

}
