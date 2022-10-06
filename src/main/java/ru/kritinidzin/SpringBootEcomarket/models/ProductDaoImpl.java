package ru.kritinidzin.SpringBootEcomarket.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("ProductDaoImpl")
public class ProductDaoImpl implements ProductDao{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public int count() throws DataAccessException {
        int count = jdbcTemplate.queryForObject("SELECT" +
                        " COUNT(*)" +
                        " FROM product"
                ,Integer.class);

        return count;
    }

    @Override
    public List<Product> selectCategory(String category) throws DataAccessException {
        List<Map<String, Object>> getList = jdbcTemplate.queryForList("SELECT * FROM product WHERE category=?", category);
        List<Product> productListByCategory = new ArrayList<>();
        for(Map<String, Object> map:getList) {
            Product product = new Product();
            product.setName((String)map.get("name"));
            product.setInformation((String)map.get("information"));
            product.setCategory((String)map.get("category"));
            product.setAmount((int)map.get("amount"));
            product.setPrice((double)map.get("price"));
            product.setStatus((int)map.get("status"));
            product.setId((Long)map.get("id"));
            productListByCategory.add(product);
        }
        return productListByCategory;
    }

    @Override
    public List<Product> selectMany() throws DataAccessException {
        List<Map<String, Object>> getList = jdbcTemplate.queryForList("SELECT * FROM product");
        List<Product> productList = new ArrayList<>();
        for(Map<String, Object> map:getList) {
            Product product = new Product();
            product.setName((String)map.get("name"));
            product.setInformation((String)map.get("information"));
            product.setCategory((String)map.get("category"));
            product.setAmount((int)map.get("amount"));
            product.setPrice((double)map.get("price"));
            product.setStatus((int)map.get("status"));
            product.setId((Long)map.get("id"));
            productList.add(product);
        }
        return productList;
    }
    @Override
    public Product selectOne(Long id) throws Exception {
        Map<String, Object> map = jdbcTemplate.queryForMap(
                "SELECT * FROM product" +
                        " WHERE id=?",
                id);
        Product product = new Product();
        product.setName((String)map.get("name"));
        product.setInformation((String)map.get("information"));
        product.setCategory((String)map.get("category"));
        product.setAmount((int)map.get("amount"));
        product.setPrice((double)map.get("price"));
        product.setStatus((int)map.get("status"));
        product.setId((Long)map.get("id"));

        return product;
    }

    @Override
    public int insertOne(Product product) throws Exception {
        int rowNumber = jdbcTemplate.update(
                "INSERT INTO product(" +
                        " name," +
                        " information," +
                        " category," +
                        " amount," +
                        " price," +
                        " status)" +
                        "VALUES(?, ?, ?, ?, ?, ?)"
                , product.getName()
                , product.getInformation()
                , product.getCategory()
                , product.getAmount()
                , product.getPrice()
                , product.getStatus());
        return rowNumber;
    }

    @Override
    public int updateOne(Product product) throws DataAccessException {
        int rowNumber = jdbcTemplate.update("UPDATE product" +
                        " SET" +
                        " name=?," +
                        " information=?," +
                        " category=?," +
                        " amount=?," +
                        " price=?," +
                        " status=?" +
                        "WHERE id=?"
                , product.getName()
                , product.getInformation()
                , product.getCategory()
                , product.getAmount()
                , product.getPrice()
                , product.getStatus()
                , product.getId());
        return rowNumber;
    }

    @Override
    public int deleteOne(Long id) throws DataAccessException {
        int rowNumber = jdbcTemplate.update("DELETE" +
                        " FROM product" +
                        " WHERE id=?"
                , id);

        return rowNumber;
    }
}
