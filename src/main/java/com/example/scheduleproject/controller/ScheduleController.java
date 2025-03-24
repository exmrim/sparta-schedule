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

    @GetMapping
    public List<ScheduleResponseDto> findAllSchedules() {
        return scheduleService.findAllSchedules();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto scheduleRequestDto
    ) {
        return new ResponseEntity<>(scheduleService.findScheduleById(id, scheduleRequestDto.getId()), HttpStatus.OK);
    }

    @GetMapping("/user/{user}")
    public ResponseEntity<ScheduleResponseDto> findScheduleByUser(@PathVariable String user) {
        return new ResponseEntity<>(scheduleService.findScheduleByUser(user), HttpStatus.OK);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @Valid @RequestBody ScheduleRequestDto scheduleRequestDto
    ) {
        return new ResponseEntity<>(scheduleService.updateSchedule(id, scheduleRequestDto.getContents(), scheduleRequestDto.getUserName(), scheduleRequestDto.getUserPw()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto scheduleRequestDto
    ) {
        scheduleService.deleteSchedule(id, scheduleRequestDto.getUserPw());

        return new ResponseEntity<>(HttpStatus.OK);
    }



}
