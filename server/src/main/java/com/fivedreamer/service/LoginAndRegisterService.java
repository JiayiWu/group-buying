package com.fivedreamer.service;

import com.fivedreamer.config.MessageInfo;
import com.fivedreamer.mapper.UserMapper;
import com.fivedreamer.model.User;
import com.fivedreamer.utils.OkHttpUtil;
import com.fivedreamer.utils.SHA256Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * Created by Jiayiwu on 17/4/30.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
@Service
public class LoginAndRegisterService {

    @Resource
    UserMapper userMapper;


    public MessageInfo register(User user){
        try {
            User u = userMapper.getUserByPhoneNumber(user.getTelephone());
            if (u != null)
                return new MessageInfo(false,"该手机号已经被注册");
            if (!registerHX(user.getTelephone(),user.getPassword()))
                return new MessageInfo(false,"用户注册失败,请稍后再试");
            user.setPassword(SHA256Util.Encrypt(user.getPassword()));
            userMapper.register(user);
            return new MessageInfo(true,user,"注册成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"注册失败");
        }
    }

    public MessageInfo login(String phoneNumber,String password){
        try{
            User u = userMapper.getUserByPhoneNumber(phoneNumber);
            if (u != null){
                if (u.getPassword().equals(SHA256Util.Encrypt(password))){
                    return new MessageInfo(true,u,"登陆成功");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"账号或密码错误");
        }
        return new MessageInfo(false,"账号或密码错误");

    }


    private boolean registerHX(String phone,String password){
        String json = "{\"username\":\"{0}\",\"password\":\"{1}\"}";
       OkHttpUtil okHttpUtil =  OkHttpUtil.getInstance();
        json = json.replace("{0}", phone).replace("{1}",password);
       return okHttpUtil.postJson("https://a1.easemob.com/1184170428115084/group/users",json);
    }

}
