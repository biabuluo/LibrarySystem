package com.example.library.Dao;

import com.example.library.Entity.User_View;
import org.springframework.data.jpa.repository.JpaRepository;

public interface User_ViewDao extends JpaRepository<User_View, Integer> {
    User_View findByUsername (String username);
    // 通过用户名查找
    User_View getByUsernameAndPassword(String username, String password);

    User_View getByUid (int uid);
    // 通过用户名和密码查找
}
