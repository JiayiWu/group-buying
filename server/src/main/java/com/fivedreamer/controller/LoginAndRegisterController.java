package com.fivedreamer.controller;

import com.fivedreamer.config.MessageInfo;
import com.fivedreamer.model.User;
import com.fivedreamer.service.LoginAndRegisterService;
import com.fivedreamer.utils.SHA256Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import javax.servlet.http.HttpSession;

/**
 * Created by Jiayiwu on 17/4/25.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */

@Controller
public class LoginAndRegisterController {

    @Resource
    LoginAndRegisterService loginAndRegisterService;

    /**
     * @param session
     * @param phoneNumber
     * @param password    用户设定的密码
     * @return MessageInfo (true表示注册成功,false表示验证码错误)
     */
    @RequestMapping("/register")
    @ResponseBody
    public MessageInfo register(HttpSession session, String phoneNumber, String password) {
        User tem = new User(phoneNumber,"用户"+phoneNumber, password,"学校未选择","男","","");
        MessageInfo messageInfo = loginAndRegisterService.register(tem);
        if (messageInfo.isResult()){
            session.setAttribute("user",messageInfo.getObject());
        }
        return messageInfo;
    }

    /**
     * @param session
     * @param phoneNumber 登录账号
     * @param password
     * @return MessageInfo (true表示登录成功,false表示账号不存在或密码错误.如登录成功,则object对象为该账号信息的VO对象)
     */
    @RequestMapping("/login")
    @ResponseBody
    public MessageInfo login(HttpSession session, String phoneNumber, String password) {


        MessageInfo messageInfo = loginAndRegisterService.login(phoneNumber,password);
        if (messageInfo.isResult()){
            session.setAttribute("user",messageInfo.getObject());
        }
        return messageInfo;
    }
}
