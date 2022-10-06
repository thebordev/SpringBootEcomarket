package ru.kritinidzin.SpringBootEcomarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.kritinidzin.SpringBootEcomarket.models.Product;
import ru.kritinidzin.SpringBootEcomarket.models.ProductDao;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    @Qualifier("ProductDaoImpl")
    ProductDao productDao;

    public List<Product> selectCategory(String category) {
        return productDao.selectCategory(category);
    }

    public int count() {
        return productDao.count();
    }

    public List<Product> selectMany() {
        return productDao.selectMany();
    }

    public Boolean insert(Product product) throws Exception {
        int rowNumber = productDao.insertOne(product);
        boolean result = false;

        if (rowNumber > 0) {
            result = true;
        }

        return result;
    }

    public Product selectOne(Long id) throws Exception {
        return productDao.selectOne(id);
    }

    public boolean updateOne(Product product) {
        int rowNumber = productDao.updateOne(product);
        boolean result = false;
        if (rowNumber > 0) {
            result = true;
        }

        return result;
    }

    public boolean deleteOne(Long id) {
        int rowNumber = productDao.deleteOne(id);
        boolean result = false;
        if (rowNumber > 0) {
            result = true;
        }
        return result;
    }
}
