package ru.kritinidzin.SpringBootEcomarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.kritinidzin.SpringBootEcomarket.models.User;
import ru.kritinidzin.SpringBootEcomarket.models.UserDao;
@Service
public class UserService {
    @Autowired
    @Qualifier("UserDaoImpl")
    UserDao userDao;

    public Boolean insert(User user) throws Exception {
        int rowNumber = userDao.insertOne(user);
        boolean result = false;

        if (rowNumber > 0) {
            result = true;
        }

        return result;
    }

    public User selectOne(String login) throws Exception {
        return userDao.selectOne(login);
    }

    public boolean updateOne(User user) {
        int rowNumber = userDao.updateOne(user);
        boolean result = false;
        if (rowNumber > 0) {
            result = true;
        }

        return result;
    }

    public boolean deleteOne(Long id) {
        int rowNumber = userDao.deleteOne(id);
        boolean result = false;
        if (rowNumber > 0) {
            result = true;
        }
        return result;
    }
}
