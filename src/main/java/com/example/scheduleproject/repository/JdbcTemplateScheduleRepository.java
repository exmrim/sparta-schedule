package com.example.scheduleproject.repository;

import com.example.scheduleproject.dto.ScheduleResponseDto;
import com.example.scheduleproject.entity.Schedule;
import com.example.scheduleproject.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class JdbcTemplateScheduleRepository implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;


    public JdbcTemplateScheduleRepository(DataSource dataSource, UserRepository userRepository) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.userRepository = userRepository;
    }

    /**
     * 일정 등록
     * @param schedule
     * @return
     */
    @Override
    public ScheduleResponseDto saveSchedule(Schedule schedule) {

        //user id 조회
        User user = userRepository.findByIdOrElseThrow(schedule.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + schedule.getUserId()));

        SimpleJdbcInsert insert = new SimpleJdbcInsert(this.jdbcTemplate);
        insert.withTableName("schedule").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", schedule.getTitle());
        parameters.put("contents", schedule.getContents());
        parameters.put("user_name", schedule.getUserName());
        parameters.put("user_id", user.getId());
        parameters.put("user_pw", schedule.getUserPw());
        parameters.put("create_date", schedule.getCreateDate());
        parameters.put("update_date", schedule.getUpdateDate());

        Number key = insert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ScheduleResponseDto(key.longValue(), schedule.getTitle(), schedule.getContents(), schedule.getUserId(), schedule.getUserPw(), schedule.getUserName(), schedule.getCreateDate(), schedule.getUpdateDate());
    }

    /**
     * 일정 목록 조회
     * @return
     */
    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        return jdbcTemplate.query("select * from schedule order by update_date, user_name desc", scheduleRowMapper());
    }

    /**
     * 일정 id에 따른 일정 조회
     * @param id
     * @return
     */
    @Override
    public Schedule findScheduleByIdOrElseThrow(Long id) {
        List<Schedule> result = jdbcTemplate.query("select * from schedule where id=?", scheduleRowMapperV2(), id);

        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule id not found, id = " + id));
    }

    /**
     * 사용자 id에 따른 일정 조회
     * @param user
     * @return
     */
    @Override
    public Schedule findScheduleByUserOrElseThrow(String user) {
        List<Schedule> result = jdbcTemplate.query("select * from schedule s join user u on s.user_id = u.id where u.user_id = ?", scheduleRowMapperV2(), user);

        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule user not found, user = " + user));
    }

    /**
     * 비밀번호 확인
     * @param id
     * @return
     */
    @Override
    public Schedule checkPw(Long id) {
        List<Schedule> result = jdbcTemplate.query("select * from schedule where id=?", scheduleRowMapperV2(), id);

        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule id not found, id = " + id));
    }

    /**
     * 일정 수정
     * @param id
     * @param contents
     * @param user_name
     * @return
     */
    @Override
    public int updateSchedule(Long id, String contents, String user_name) {
        return jdbcTemplate.update("update schedule set contents=?, user_name=?, update_date=? where id=?", contents, user_name, LocalDateTime.now(), id);
    }

    /**
     * 일정 삭제
     * @param id
     * @return
     */
    @Override
    public int deleteSchedule(Long id) {
        return jdbcTemplate.update("delete from schedule where id=?", id);
    }

    /**
     * 일정 등록 mapper
     * @return
     */
    private RowMapper<ScheduleResponseDto> scheduleRowMapper() {
        return new RowMapper<ScheduleResponseDto>() {

            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getString("user_id"),
                        rs.getString("user_pw"),
                        rs.getString("user_name"),
                        rs.getTimestamp("create_date").toLocalDateTime(),
                        rs.getTimestamp("update_date").toLocalDateTime()
                );
            }
        };
    }

    /**
     * mapper
     * @return
     */
    private RowMapper<Schedule> scheduleRowMapperV2() {
        return new RowMapper<Schedule>() {

            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getString("user_id"),
                        rs.getString("user_pw"),
                        rs.getString("user_name"),
                        rs.getTimestamp("create_date").toLocalDateTime(),
                        rs.getTimestamp("update_date").toLocalDateTime()

                );
            }
        };
    }


}
