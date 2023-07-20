package com.example.library.Service;

import com.example.library.Dao.CommentDao;
import com.example.library.Dao.Comment_ViewDao;
import com.example.library.Dao.User_Comment_ViewDao;
import com.example.library.Entity.Comment;
import com.example.library.View.Comment_View;
import com.example.library.View.User_Comment_View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    Comment_ViewDao commentViewDao;

    @Autowired
    User_Comment_ViewDao user_comment_viewDao;

    @Autowired
    CommentDao commentDao;

    public List<Comment_View> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "commdate");
        return commentViewDao.findAll(sort);
    }

    public List<User_Comment_View> listByNick (String nick) {
        Sort sort = new Sort(Sort.Direction.DESC, "commdate");
        return user_comment_viewDao.findAllByNick(nick);
    }

    public void add(Comment comment) {
        commentDao.save(comment);
    }
}
