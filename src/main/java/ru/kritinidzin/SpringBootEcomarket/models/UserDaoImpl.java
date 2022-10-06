package ru.kritinidzin.SpringBootEcomarket.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("UserDaoImpl")
public class UserDaoImpl implements UserDao{

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User selectOne(String login) throws Exception {
        Map<String, Object> map = jdbcTemplate.queryForMap(
                "SELECT * FROM user" +
                " WHERE login=?",
                login);
        User user = new User();
        user.setLogin((String)map.get("login"));
        user.setPassword((String)map.get("password"));
        user.setRole((String)map.get("role"));

        return user;
    }

    @Override
    public int insertOne(User user) throws Exception {
        String password = passwordEncoder.encode(user.getPassword());
        int rowNumber = jdbcTemplate.update(
                "INSERT INTO user(" +
                        " login," +
                        " password," +
                        " role)" +
                        "VALUES(?, ?, ?)"
                , user.getLogin()
                , password
                , user.getRole());
        return rowNumber;
    }

    @Override
    public int updateOne(User user) throws DataAccessException {
        String password = passwordEncoder.encode(user.getPassword());
        int rowNumber = jdbcTemplate.update("UPDATE user" +
                " SET" +
                " password=?," +
                " login=?" +
                " WHERE id=?"
        , password
        , user.getLogin()
        , user.getId());
        return rowNumber;
    }

    @Override
    public int deleteOne(Long id) throws DataAccessException {
        int rowNumber = jdbcTemplate.update("UPDATE" +
                " FROM user" +
                " WHERE id=?"
                , id);

        return rowNumber;
    }
}
