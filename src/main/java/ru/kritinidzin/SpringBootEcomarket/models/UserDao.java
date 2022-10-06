package ru.kritinidzin.SpringBootEcomarket.models;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

public interface UserDao {
    public User selectOne(String login) throws Exception;
    public int insertOne(User user) throws Exception;
    public int updateOne(User user) throws DataAccessException;
    public int deleteOne(Long id) throws DataAccessException;
}
