package com.example.library.Dao;

import com.example.library.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;

public interface CategoryDao extends JpaRepository<Category, Integer> {
    @Modifying
    @Transactional
    public void removeByCid (int cid);

    public Category findByCid (int cid);

    public Category findByCname (String cname);
}
