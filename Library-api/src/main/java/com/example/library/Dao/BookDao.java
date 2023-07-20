package com.example.library.Dao;

import com.example.library.Entity.Book;
import com.example.library.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

public interface BookDao extends JpaRepository<Book, Integer> {
    public Book findByBid (int bid);
    public List<Book> findAllByCategory (Category category);
    public Book findByISBN (String ISBN);
    public List<Book> findAllByTitleLikeOrAuthorLike (String title, String author);
    // 通过作者查找

    @Modifying
    @Transactional
    void removeByBid (int bid);
}
