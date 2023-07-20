package com.example.library.Controller;

import com.example.library.Entity.User_View;
import com.example.library.Result.Result;
import com.example.library.Result.ResultFactory;
import com.example.library.Service.User_ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;


import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    User_ViewService user_viewService;

    @CrossOrigin
    @PostMapping(value = "/api/login")
    @ResponseBody
    public Result login(@RequestBody User_View requestUser) {
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);

        String password = requestUser.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        User_View user = user_viewService.get(username, password);
        if (user == null) {
            return ResultFactory.buildFailResult("登陆失败");
        } else {
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("isAdmin", user.getIsadmin());
            data.put("uid", user.getUid());
            return ResultFactory.buildSuccessResult(data);
        }
    }
}

