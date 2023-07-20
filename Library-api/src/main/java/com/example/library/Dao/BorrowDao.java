package com.example.library.Dao;

import com.example.library.Entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

public interface BorrowDao extends JpaRepository<Borrow, Integer> {
    public Borrow findByUidAndBid (int uid, int bid);
    @Modifying
    @Transactional
    public void removeByUidAndBid (int uid, int bid);

    public List<Borrow> findAllByBid (int bid);
}
