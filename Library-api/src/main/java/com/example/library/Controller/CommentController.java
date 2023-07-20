package com.example.library.Controller;

import com.example.library.Entity.Comment;
import com.example.library.Result.Result;
import com.example.library.Result.ResultFactory;
import com.example.library.Service.BookService;
import com.example.library.Service.CommentService;
import com.example.library.Service.User_ViewService;
import com.example.library.View.Comment_View;
import com.example.library.View.User_Comment_View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    User_ViewService userService;

    @Autowired
    BookService bookService;

    @CrossOrigin
    @GetMapping("/api/comment")
    public List<Comment_View> list () {
        return commentService.list();
    }

    @CrossOrigin
    @PostMapping("/api/add/comment")
    public Result addComment (@RequestBody Comment comment) {
        if (bookService.findByISBN(comment.getIsbn()) == null) return ResultFactory.buildFailResult("没有此书");
        Date date = new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd");
        String commdate = dateFormat.format(date);
        comment.setCommdate(commdate);
        commentService.add(comment);
        return ResultFactory.buildSuccessResult("");
    }

    @CrossOrigin
    @GetMapping("/api/{uid}/comment")
    public List<User_Comment_View> listByNick(@PathVariable("uid") int uid) {
        String nick = userService.getByUid(uid).getNick();
        return commentService.listByNick(nick);
    }
}
