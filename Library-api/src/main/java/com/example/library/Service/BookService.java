package com.example.library.Service;

import com.example.library.Entity.Book;
import com.example.library.Dao.BookDao;
import com.example.library.Entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookService {
    @Autowired
    BookDao bookDao;
    @Autowired
    CategoryService categoryService;

    public List<Book> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "bid");
        return bookDao.findAll(sort);
    }


    public void addOrUpdate(Book book) {
        bookDao.save(book);
    }

    public Book findByBid (int bid) { return bookDao.findByBid(bid);}

    public Book findByISBN (String ISBN) {return bookDao.findByISBN(ISBN);}

    public List<Book> Search (String keywords) {return bookDao.findAllByTitleLikeOrAuthorLike('%' + keywords + '%', '%' + keywords + '%');}

    public void deleteById(int id) {
        bookDao.deleteById(id);
    }

    public List<Book> listByCategory(int cid) {
        Category category = categoryService.get(cid);
        return bookDao.findAllByCategory(category);
    }

    public void removeByBid (int bid) {
        bookDao.removeByBid(bid);
    }
}
