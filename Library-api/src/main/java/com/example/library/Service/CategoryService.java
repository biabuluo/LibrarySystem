package com.example.library.Service;

import com.example.library.Dao.CategoryDao;
import com.example.library.Entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryDao categoryDao;


    public List<Category> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "cid");
        return categoryDao.findAll(sort);
    }

    public void addOrUpdate (Category category) {
        categoryDao.save(category);
    }

    public void removeByCid (int cid) {
        categoryDao.removeByCid(cid);
    }

    public Category get(int cid) {
        Category c= categoryDao.findByCid(cid);
        return c;
    }

    public Category getCname (String cname) {
        return categoryDao.findByCname(cname);
    }
}

