package com.example.library.Controller;

import com.example.library.Entity.User;
import com.example.library.Entity.User_View;
import com.example.library.Result.Result;
import com.example.library.Result.ResultFactory;
import com.example.library.Service.UserService;
import com.example.library.Service.User_ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    User_ViewService user_viewService;

    @CrossOrigin
    @ResponseBody
    @GetMapping(value = "/api/{uid}/user_info")
    public User_View getUserInfo (@PathVariable("uid") int uid) {
        return user_viewService.getByUid(uid);
    }

    @CrossOrigin
    @PostMapping(value = "/api/adduser")
    @ResponseBody
    public Result adduser (@RequestBody User user) {
        User temp = userService.findByUsername(user.getUsername());
        if (temp != null) {
            if (temp.getPassword().equals(user.getPassword())) {
                userService.add(user);
                return  ResultFactory.buildSuccessResult("");
            }
        }
        String password = user.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setPassword(password);
        userService.add(user);
        return  ResultFactory.buildSuccessResult("");
    }

    @CrossOrigin
    @GetMapping(value = "/api/user/list")
    @ResponseBody
    public List<User> list() {
        return userService.list();
    }

    @CrossOrigin
    @PostMapping(value = "/api/user/delete")
    @ResponseBody
    public Result delete(@RequestParam("keyword") String keyword) {
        if (user_viewService.getByUsername(keyword).getBorrownums() == 0) {
            userService.remove(keyword);
            return ResultFactory.buildSuccessResult("删除成功");
        }
        return ResultFactory.buildFailResult("没还完书");
    }

    @CrossOrigin
    @GetMapping(value = "/api/user/search")
    @ResponseBody
    public List<User> list(@RequestParam("keyword") String keyword) {
        if (keyword == null) return list();
        return userService.rearch(keyword);
    }

}
