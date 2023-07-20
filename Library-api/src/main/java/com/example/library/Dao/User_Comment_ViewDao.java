package com.example.library.Dao;

import com.example.library.View.User_Comment_View;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface User_Comment_ViewDao extends JpaRepository<User_Comment_View, Integer> {
    public List<User_Comment_View> findAllByNick(String nick);
}
