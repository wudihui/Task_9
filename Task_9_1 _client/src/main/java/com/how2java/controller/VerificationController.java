package com.how2java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/8/9.
 */

@Controller
public class VerificationController {
    @RequestMapping(value = "/com/how2java/verification",method = RequestMethod.POST)
    public void var(HttpServletRequest request){
        String email=request.getParameter("email");
        System.out.println(email);
    }
}
