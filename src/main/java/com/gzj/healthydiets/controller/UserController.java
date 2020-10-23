package com.gzj.healthydiets.controller;
import com.gzj.healthydiets.entity.User;
import com.gzj.healthydiets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 功能1：用户注销
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        //1、删除session的user信息或者销毁session
       session.invalidate();
        //2、重定向到主页（或者登录页面）
        return "redirect:/";
    }

    /**
     * 功能2：用户登录
     */
    @RequestMapping("/login")
    public String login(@ModelAttribute User user, HttpSession session,Model model) {

        User userDB = userService.queryUserByUsername(user.getUsername());
        if (userDB != null) {
            if (user.getPassword().equals(userDB.getPassword())) {
                session.setAttribute("user", userDB);
                return  "forward:/food/page/1";
            } else {
                //用户名密码错误
                model.addAttribute("msg", "用户密码错误！");
                model.addAttribute("username", user.getUsername());
                return "user/login";
            }
        } else {
            //用户名不存在
            model.addAttribute("msg", "用户名不存在！");
            model.addAttribute("username", user.getUsername());
            return "user/login";
        }
    }

    /**
     * 功能3：注册时：验证用户名是否存在
     *
     * @return
     */
    @RequestMapping("/existUsername")
    @ResponseBody
    public Map<String, Boolean> existUsername(String username) {
        boolean b = userService.existUsername(username);

        //3、将数据封装到json中
        Map<String, Boolean> data = new HashMap<>();
        data.put("existUsername", b);
        return data;
    }

    /**
     * 功能4：用户注册
     */
    @RequestMapping("/register")
    public String register(@ModelAttribute User user, HttpServletRequest request) {
        userService.registerUser(user);
        User userDB = userService.queryUserByUsername(user.getUsername());
        request.getSession().setAttribute("user", userDB);
        return  "forward:/food/page/1";
    }
}