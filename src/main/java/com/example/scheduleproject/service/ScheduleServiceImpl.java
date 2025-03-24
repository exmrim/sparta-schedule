package com.example.scheduleproject.service;

import com.example.scheduleproject.dto.*;
import com.example.scheduleproject.entity.Schedule;
import com.example.scheduleproject.repository.ScheduleRepository;
import com.example.scheduleproject.exception.InvalidHandler;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    /**
     * 일정 등록
     * @param scheduleRequestDto
     * @return
     */
    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto) {

        Schedule schedule = new Schedule(scheduleRequestDto.getTitle(), scheduleRequestDto.getContents(), scheduleRequestDto.getUserName(), scheduleRequestDto.getUserId(), scheduleRequestDto.getUserPw(), scheduleRequestDto.getCreateDate(), scheduleRequestDto.getUpdateDate());

        invalidException(schedule);

        return scheduleRepository.saveSchedule(schedule);

    }

    /**
     * 일정 목록 조회
     * @return
     */
    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        return scheduleRepository.findAllSchedules();
    }

    /**
     * 일정 id에 따른 일정 조회
     * @param id
     * @param inputId
     * @return
     */
    @Override
    public ScheduleResponseDto findScheduleById(Long id, Long inputId) {

        //삭제된 정보
        checkScheduleException(id, inputId);

        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);
        return new ScheduleResponseDto(schedule);
    }

    /**
     * 사용자 id에 따른 일정 조회
     * @param user
     * @return
     */
    @Override
    public ScheduleResponseDto findScheduleByUser(String user) {

        //잘못된 정보
        checkUserException(user);

        Schedule schedule = scheduleRepository.findScheduleByUserOrElseThrow(user);
        return new ScheduleResponseDto(schedule);
    }

    /**
     * 일정 수정
     * @param id
     * @param contents
     * @param user_name
     * @param user_pw
     * @return
     */
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

    /**
     * 일정 삭제
     * @param id
     * @param user_pw
     */
    @Override
    public void deleteSchedule(Long id, String user_pw) {

        checkPwException(id, user_pw);

        int deleteRow = scheduleRepository.deleteSchedule(id);

        if(deleteRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule id not found. id = " + id);
        }
    }

    /**
     * 비밀번호 가져오기
     * @param id
     * @return
     */
    @Override
    public ScheduleResponseDto checkPw(Long id) {
        Schedule schedule = scheduleRepository.checkPw(id);
        return new ScheduleResponseDto(schedule);
    }

    /**
     * 잘못된 정보(사용자) 예외 처리
     * @param user
     * @return
     */
    public boolean checkUserException(String user) {
        String userId = findScheduleByUser(user).getUserId();

        if(userId == null) {
            throw new InvalidHandler("UserNull","User not found.");
        }
        return true;
    }

    /**
     * 비밀번호 확인 예외처리
     * @param id
     * @param inputPw
     * @return
     */
    public boolean checkPwException(Long id, String inputPw) {
        String userPw = checkPw(id).getUserPw();

        if(userPw == null) {
            throw new InvalidHandler("UserPasswordNull","User Password not found.");
        } else if(!userPw.equals(inputPw)) {
            throw new InvalidHandler("UserPasswordMatch","Password does not match.");
        }
        return true;
    }

    /**
     * 삭제되지 않은 일정 예외처리
     * @param id
     * @param inputId
     * @return
     */
    public boolean checkScheduleException(Long id, Long inputId) {
        Long num = findAllSchedules().get(0).getId();

        if(num == null) {
            throw new InvalidHandler("ScheduleNull","Schedule not found.");
        } else if(num > inputId) {
            throw new InvalidHandler("ScheduleDelete","Schedule is deleted.");
        }
        return true;
    }

    /**
     * 유효성 검사
     * @param schedule
     * @return
     */
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
