package ru.kritinidzin.SpringBootEcomarket.models;

import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ProductDao {
    public int count() throws DataAccessException;
    public List<Product> selectCategory(String category) throws DataAccessException;
    public List<Product> selectMany() throws DataAccessException;
    public Product selectOne(Long id) throws Exception;
    public int insertOne(Product product) throws Exception;
    public int updateOne(Product product) throws DataAccessException;
    public int deleteOne(Long id) throws DataAccessException;
}
