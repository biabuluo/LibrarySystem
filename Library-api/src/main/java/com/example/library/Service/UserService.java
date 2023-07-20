package com.example.library.Service;

import com.example.library.Dao.UserDao;
import com.example.library.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public List<User> list() {
        Sort sort = new Sort(Sort.Direction.ASC, "uid");
        return userDao.findAll(sort);
    }

    public void remove(String username) {
        userDao.removeByUsername(username);
    }

    public List<User> rearch (String keyword) {
        return userDao.findByUsernameLikeOrNameLike("%" + keyword + "%", "%" + keyword + "%");
    }

    public User findByUsername (String username) {
        return userDao.findByUsername(username);
    }

    public void add (User user) {
        userDao.save(user);
    }
}
