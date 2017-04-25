package com.fivedreamer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.security.auth.message.MessageInfo;
import javax.servlet.http.HttpSession;

/**
 * Created by Jiayiwu on 17/4/25.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */

@Controller
public class LoginAndRegisterController {

    /**
     * @param session
     * @param phoneNumber
     * @return MessageInfo (true表示短信发送成功,false表示短信发送失败或电话无效)
     */
    @RequestMapping("/register/message")
    @ResponseBody
    public MessageInfo sendMessage(HttpSession session,String phoneNumber){
        return null;
    }

    /**
     * @param session
     * @param phoneNumber
     * @param password 用户设定的密码
     * @param verificationCode 发送短信时接收的验证码
     * @return MessageInfo (true表示注册成功,false表示验证码错误)
     */
    @RequestMapping("/register")
    @ResponseBody
    public MessageInfo register(HttpSession session,String phoneNumber,String password,String verificationCode ){
        return null;
    }

    /**
     * @param session
     * @param phoneNumber 登录账号
     * @param password
     * @return MessageInfo (true表示登录成功,false表示账号不存在或密码错误.如登录成功,则object对象为该账号信息的VO对象)
     */
    @RequestMapping("/login")
    @ResponseBody
    public MessageInfo login(HttpSession session,String phoneNumber,String password){
        return null;
    }
}
