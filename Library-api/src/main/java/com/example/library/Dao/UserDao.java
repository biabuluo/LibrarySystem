package com.example.library.Dao;

import com.example.library.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {
    public List<User> findByUsernameAndName (String username, String name);
    public List<User> findByUsernameLikeOrNameLike (String username, String name);

    public User findByUsername (String username);

    @Modifying
    @Transactional
    public void removeByUsername (String username);
}
