package com.example.scheduleproject.repository;

import com.example.scheduleproject.dto.UserResponseDto;
import com.example.scheduleproject.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class JdbcTemplateUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateUserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 사용자 등록
     * @param user
     * @return
     */
    @Override
    public UserResponseDto saveUser(User user) {

        SimpleJdbcInsert insert = new SimpleJdbcInsert(this.jdbcTemplate);
        insert.withTableName("user").usingGeneratedKeyColumns("id");

        String uuid = UUID.randomUUID().toString();

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("user_id", user.getUserId());
        parameters.put("user_pw", user.getUserPw());
        parameters.put("user_name", user.getUserName());
        parameters.put("user_email", user.getUserEmail());
        parameters.put("age", user.getAge());
        parameters.put("job", user.getJob());
        parameters.put("create_date", user.getCreateDate());
        parameters.put("update_date", user.getUpdateDate());

        insert.execute(new MapSqlParameterSource(parameters));

        return new UserResponseDto(uuid, user.getUserId(), user.getUserPw(), user.getUserName(), user.getUserEmail(), user.getAge(), user.getJob(), user.getCreateDate(), user.getUpdateDate());

    }

    /**
     * 사용자 id 조회
     * @param userId
     * @return
     */
    @Override
    public Optional<User> findByIdOrElseThrow(String userId) {
        List<User> result = jdbcTemplate.query("select * from user where user_id=?", userRowMapperV2(), userId);
        System.out.println(result);
        return result.stream().findAny();
        }

    /**
     * mapper
     * @return
     */
    private RowMapper<User> userRowMapperV2() {
        return new RowMapper<User>() {

            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new User(
                        rs.getString("id"),
                        rs.getString("user_id"),
                        rs.getString("user_pw"),
                        rs.getString("user_name"),
                        rs.getString("user_email"),
                        rs.getInt("age"),
                        rs.getString("job"),
                        rs.getTimestamp("create_date").toLocalDateTime(),
                        rs.getTimestamp("update_date").toLocalDateTime()

                );
            }
        };
    }

}
