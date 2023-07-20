package com.example.library.Controller;

import com.example.library.Entity.Book;
import com.example.library.Entity.Borrow;
import com.example.library.Entity.Category;
import com.example.library.Result.Result;
import com.example.library.Result.ResultFactory;
import com.example.library.Service.BookService;
import com.example.library.Service.BorrowService;
import com.example.library.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class LibraryController {
    @Autowired
    BookService bookService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    BorrowService borrowService;
    @CrossOrigin
    @GetMapping ("/api/books")
    public List<Book> list(){
        return bookService.list();
    }

    @CrossOrigin
    @GetMapping("/api/categories/{cid}/books")
    public List<Book> listByCategory(@PathVariable("cid") int cid){
        if (0 != cid) {
            return bookService.listByCategory(cid);
        } else {
            return list();
        }
    }

    @CrossOrigin
    @GetMapping("/api/searchbook/{bid}")
    public Book findBookByBid (@PathVariable("bid") int bid) {
        return bookService.findByBid(bid);
    }

    @CrossOrigin
    @GetMapping ("/api/searchBook")
    public List<Book> searchResult (@RequestParam("keyword") String keyword) {
        if ("".equals(keyword)) {
            return bookService.list();
        } else {
            return bookService.Search(keyword);
        }
    }

    @CrossOrigin
    @PostMapping("/api/addbook/{cid}")
    public Book addBook (@RequestBody Book book, @PathVariable("cid") int cid){
        if (book.getCover().equals("")) book.setCover("https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png");
        Category category = categoryService.get(cid);
        book.setCategory(category);
        bookService.addOrUpdate(book);
        return book;
    }


    @CrossOrigin
    @PostMapping("/api/deletebook/{bid}")
    public Result deleteBook (@PathVariable("bid") int bid) {
        if (borrowService.findAllByBid(bid).isEmpty()) {
            bookService.removeByBid(bid);
            return ResultFactory.buildSuccessResult("");
        }
        return ResultFactory.buildFailResult("");
    }

    @CrossOrigin
    @GetMapping("/api/category")
    public List<Category> categories() {
        return categoryService.list();
    }

    @CrossOrigin
    @GetMapping("/api/category/{cname}")
    public List<Category> searchcategory (@PathVariable("cname") String cname) {
        if (cname.equals("")) return categories();
        else {
            List<Category> categories = new ArrayList<Category>();
            categories.add(categoryService.getCname(cname));
            return categories;
        }
    }

    @CrossOrigin
    @PostMapping("/api/borrow")
    public Result borrow (@RequestBody Borrow borrow) {
        int uid = borrow.getUid();
        int bid = borrow.getBid();
        Book book = bookService.findByBid(bid);

        int num = book.getNums();

        if (num != 0) {
            borrow = borrowService.findByUidAndBid(uid, bid);
            if (borrow == null) {
                borrow = new Borrow();
                book.setNums(num-1);
                bookService.addOrUpdate(book);
                Date date = new Date();
                SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd");
                String borrowdate = dateFormat.format(date);
                borrow.setUid(uid);
                borrow.setBid(bid);
                borrow.setBorrowdate(borrowdate);
                borrowService.addOrUpdate(borrow);
                return ResultFactory.buildSuccessResult("");
            } else {
                return ResultFactory.buildFailResult("已借过");
            }
        }

        return ResultFactory.buildFailResult("书籍数量为0");
    }

    @CrossOrigin
    @PostMapping("/api/deleteborrow")
    public Result delete (@RequestParam("uid") int uid, @RequestParam("bid") int bid) {
        Book book = bookService.findByBid(bid);
        book.setNums(book.getNums()+1);
        bookService.addOrUpdate(book);
        borrowService.removeByUidAndBid(uid, bid);
        return ResultFactory.buildSuccessResult("");
    }

    @CrossOrigin
    @PostMapping("/api/addcategory")
    public Result addCategory (@RequestBody Category category) {
        if (category.getIcon().equals("")) category.setIcon("el-icon-loading");
        categoryService.addOrUpdate(category);
        return ResultFactory.buildSuccessResult("");
    }

    @CrossOrigin
    @PostMapping("/api/{cid}/deletecategory")
    public Result delete (@PathVariable("cid") int cid) {
        categoryService.removeByCid(cid);
        return ResultFactory.buildSuccessResult("");
    }

}
