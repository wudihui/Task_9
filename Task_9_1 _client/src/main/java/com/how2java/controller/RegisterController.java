package com.how2java.controller;

import com.how2java.util.MemcachedUtil;
import com.how2java.verification.NetEaseYunxin;
import com.how2java.mapper.CategoryMapper;
import com.how2java.pojo.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/8/5.
 */

@Controller
public class RegisterController {

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    NetEaseYunxin netEaseYunxin;

    final String name_error = "该用户名被占用,请换一个再试...";
    final String email_error = "该邮箱已经注册,请登录...";

    //注册
    @RequestMapping(value = "/ok", method = RequestMethod.POST)
    public String ok(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (categoryMapper.getMP(email) != null) {
            Register register = categoryMapper.getMP(email);
            if (register.getUsername().equals(username)) {
                model.addAttribute("name_error", name_error);
                return "register";
            }
            model.addAttribute("email_error", email_error);
            return "register";
        }
//        Pattern p=Pattern.compile("(\\d{11})");
//        Matcher m=p.matcher(email);
//        if (m.find()){
//            String phone=m.group(1);
//            System.out.println(phone);
//            MemcachedUtil.add(phone,netEaseYunxin.send(phone),new Date(1000*60*5));
//        }
        Register register = new Register();
        register.setUsername(username);
        register.setEmail(email);
        register.setPassword(password);
        categoryMapper.register(register);
        model.addAttribute("name", username);
        return "ok";
    }
}
