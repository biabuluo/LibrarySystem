package com.example.library.Service;

import com.example.library.Dao.Borrow_viewDao;
import com.example.library.View.Borrow_view;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Borrow_viewService {
    @Autowired
    Borrow_viewDao borrow_viewDao;

    public List<Borrow_view> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "borrowdate");
        return borrow_viewDao.findAll(sort);
    }

    public List<Borrow_view> listByUid (int uid) {
        return borrow_viewDao.findAllByUid(uid);
    }

    public void removeByUidAndBid (int uid, int bid) {borrow_viewDao.removeByUidAndBid(uid, bid);}

    public Borrow_view findByUidAndBid (int uid, int bid) {return borrow_viewDao.findByUidAndBid(uid, bid);}

    public List<Borrow_view> listByBid (int bid) {
        return borrow_viewDao.findAllByBid(bid);
    }

}
