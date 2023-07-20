package com.example.library.Service;

import com.example.library.Dao.BorrowDao;
import com.example.library.Entity.Borrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowService {
    @Autowired
    BorrowDao borrowDao;

    public Borrow findByUidAndBid (int uid, int bid) {
        return borrowDao.findByUidAndBid(uid, bid);
    }

    public List<Borrow> findAllByBid (int bid) {
        return borrowDao.findAllByBid(bid);
    }

    public void removeByUidAndBid (int uid, int bid) {
        borrowDao.removeByUidAndBid(uid, bid);
    }

    public void addOrUpdate (Borrow borrow){
        borrowDao.save(borrow);
    }
}
