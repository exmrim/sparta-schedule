package com.example.scheduleproject.controller;

import com.example.scheduleproject.dto.*;
import com.example.scheduleproject.entity.Schedule;
import com.example.scheduleproject.service.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    private ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /**
     * 일정 생성 API
     * @param scheduleRequestDto
     * @return
     */
    @PostMapping()
    public ResponseEntity<ScheduleResponseDto> createSchedule(
            @Valid @RequestBody ScheduleRequestDto scheduleRequestDto) {
        return new ResponseEntity<>(scheduleService.saveSchedule(scheduleRequestDto), HttpStatus.CREATED);
    }

    /**
     * 일정 목록 조회
     * @return
     */
    @GetMapping
    public List<ScheduleResponseDto> findAllSchedules() {
        return scheduleService.findAllSchedules();
    }

    /**
     * 일정 id에 따른 일정 조회
     * @param id
     * @param scheduleRequestDto
     * @return
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto scheduleRequestDto
    ) {
        return new ResponseEntity<>(scheduleService.findScheduleById(id, scheduleRequestDto.getId()), HttpStatus.OK);
    }

    /**
     * 사용자 id에 따른 일정 조회
     * @param user
     * @return
     */
    @GetMapping("/user/{user}")
    public ResponseEntity<ScheduleResponseDto> findScheduleByUser(@PathVariable String user) {
        return new ResponseEntity<>(scheduleService.findScheduleByUser(user), HttpStatus.OK);
    }

    /**
     * 일정 수정
     * @param id
     * @param scheduleRequestDto
     * @return
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @Valid @RequestBody ScheduleRequestDto scheduleRequestDto
    ) {
        return new ResponseEntity<>(scheduleService.updateSchedule(id, scheduleRequestDto.getContents(), scheduleRequestDto.getUserName(), scheduleRequestDto.getUserPw()), HttpStatus.OK);
    }

    /**
     * 일정 삭제
     * @param id
     * @param scheduleRequestDto
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto scheduleRequestDto
    ) {
        scheduleService.deleteSchedule(id, scheduleRequestDto.getUserPw());

        return new ResponseEntity<>(HttpStatus.OK);
    }



}
