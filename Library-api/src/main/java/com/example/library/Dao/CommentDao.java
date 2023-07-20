package com.example.library.Dao;

import com.example.library.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comment, Integer> {
}
