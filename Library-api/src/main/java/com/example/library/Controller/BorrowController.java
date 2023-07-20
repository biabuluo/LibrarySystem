package com.example.library.Controller;

import com.example.library.Entity.Book;
import com.example.library.Result.Result;
import com.example.library.Result.ResultFactory;
import com.example.library.Service.BookService;
import com.example.library.Service.Borrow_viewService;
import com.example.library.Service.UserService;
import com.example.library.View.Borrow_view;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BorrowController {
    @Autowired
    Borrow_viewService borrow_viewService;

    @Autowired
    BookService bookService;

    @Autowired
    UserService userService;

    @CrossOrigin
    @GetMapping("/api/{uid}/borrow_view")
    public List<Borrow_view> listByUid(@PathVariable("uid") int uid) throws Exception {
        if (uid != 0) {
            return borrow_viewService.listByUid(uid);
        } else {
            return null;
        }
    }

    @CrossOrigin
    @GetMapping("/api/searchborrow")
    public List<Borrow_view> search (@RequestParam("username") String username, @RequestParam("isbn") String isbn) {
        if (username.equals("") && isbn.equals("")) {
            return borrow_viewService.list();
        } else {
            if (username.equals("")) {
                int bid = bookService.findByISBN(isbn).getBid();
                return borrow_viewService.listByBid(bid);
            } else if (isbn.equals("")) {
                int uid = userService.findByUsername(username).getUid();
                return borrow_viewService.listByUid(uid);
            } else {
                int bid = bookService.findByISBN(isbn).getBid();
                int uid = userService.findByUsername(username).getUid();
                List<Borrow_view> borrow_views = new ArrayList<Borrow_view>();
                borrow_views.add(borrow_viewService.findByUidAndBid(uid, bid));
                return borrow_views;
            }
        }
    }

}
