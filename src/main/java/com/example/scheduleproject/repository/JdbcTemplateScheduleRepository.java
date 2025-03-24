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

    @Override
    public ScheduleResponseDto saveSchedule(Schedule schedule) {

        User user = userRepository.findByIdOrElseThrow(schedule.getUser_id())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + schedule.getUser_id()));

        SimpleJdbcInsert insert = new SimpleJdbcInsert(this.jdbcTemplate);
        insert.withTableName("schedule").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", schedule.getTitle());
        parameters.put("contents", schedule.getContents());
        parameters.put("user_name", schedule.getUser_name());
        parameters.put("user_id", user.getId());
        parameters.put("user_pw", schedule.getUser_pw());
        parameters.put("create_date", schedule.getCreate_date());
        parameters.put("update_date", schedule.getUpdate_date());

        Number key = insert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ScheduleResponseDto(key.longValue(), schedule.getTitle(), schedule.getContents(), schedule.getUser_id(), schedule.getUser_pw(), schedule.getUser_name(), schedule.getCreate_date(), schedule.getUpdate_date());
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        return jdbcTemplate.query("select * from schedule order by update_date, user_name desc", scheduleRowMapper());
    }

    /*
    @Override
    public Optional<Schedule> findScheduleById(Long id) {
        List<Schedule> result = jdbcTemplate.query("select * from schedule where id=?", scheduleRowMapperV2(), id);

        return result.stream().findAny();
    }
    */

    @Override
    public Schedule findScheduleByIdOrElseThrow(Long id) {
        List<Schedule> result = jdbcTemplate.query("select * from schedule where id=?", scheduleRowMapperV2(), id);

        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule id not found, id = " + id));
    }

    @Override
    public Schedule findScheduleByUserOrElseThrow(String user) {
        List<Schedule> result = jdbcTemplate.query("select * from schedule s join user u on s.user_id = u.id where u.user_id = ?", scheduleRowMapperV2(), user);

        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule user not found, user = " + user));
    }

    @Override
    public Schedule checkPw(Long id) {
        List<Schedule> result = jdbcTemplate.query("select * from schedule where id=?", scheduleRowMapperV2(), id);

        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule id not found, id = " + id));
    }

    @Override
    public int updateSchedule(Long id, String contents, String user_name) {
        return jdbcTemplate.update("update schedule set contents=?, user_name=?, update_date=? where id=?", contents, user_name, LocalDateTime.now(), id);
    }

    @Override
    public int deleteSchedule(Long id) {
        return jdbcTemplate.update("delete from schedule where id=?", id);
    }

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
