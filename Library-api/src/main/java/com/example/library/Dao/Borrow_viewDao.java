package com.example.library.Dao;

import com.example.library.View.Borrow_view;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

public interface Borrow_viewDao extends JpaRepository<Borrow_view, Integer> {
    public List<Borrow_view> findAllByUidAndBid (int uid, int bid);
    public List<Borrow_view> findAllByUid (int uid);

    public List<Borrow_view> findAllByBid (int bid);

    @Modifying
    @Transactional
    public void removeByUidAndBid (int uid, int bid);

    public Borrow_view findByUidAndBid (int uid, int bid);
}
