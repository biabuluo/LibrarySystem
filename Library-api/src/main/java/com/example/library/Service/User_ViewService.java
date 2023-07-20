package com.example.library.Service;

import com.example.library.Dao.User_ViewDao;
import com.example.library.Entity.User_View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class User_ViewService {
    @Autowired
    User_ViewDao userViewDao;

    public boolean isExist(String username) {
        User_View user = getByUsername(username);
        return null!=user;
    }

    public User_View getByUsername(String username) {
        return userViewDao.findByUsername(username);
    }

    public User_View get(String username, String password){
        return userViewDao.getByUsernameAndPassword(username, password);
    }

    public User_View getByUid (int uid) {
        return  userViewDao.getByUid(uid);
    }

    public void add(User_View user) {
        userViewDao.save(user);
    }
}
