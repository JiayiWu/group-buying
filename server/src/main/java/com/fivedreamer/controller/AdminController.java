package com.fivedreamer.controller;

import com.fivedreamer.config.MessageInfo;
import com.fivedreamer.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by Jiayiwu on 17/5/3.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
@Controller
public class AdminController {

    @Resource
    AdminService adminService;

    @RequestMapping("/ad/login")
    @ResponseBody
    public MessageInfo login(HttpSession session,String username, String password){
        MessageInfo messageInfo = adminService.login(username,password);
        if (messageInfo.isResult()){
            session.setAttribute("admin",messageInfo.getObject());
        }
        return messageInfo;
    }

    @RequestMapping("/ad/reset")
    @ResponseBody
    public MessageInfo updatePassword(HttpSession session,String oldpassword,String newpassword){

        return adminService.modifyPassword(oldpassword,newpassword);
    }
}
