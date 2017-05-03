package com.fivedreamer.service;

import com.fivedreamer.config.MessageInfo;
import com.fivedreamer.mapper.AdminMapper;
import com.fivedreamer.model.Admin;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Jiayiwu on 17/5/3.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
@Service
public class AdminService {


    @Resource
    AdminMapper adminMapper;


    public MessageInfo login(String username,String password){
        try{
            Admin admin = adminMapper.getAdmin();
            if (admin.getPassword().equals(password)&&admin.getUsername().equals(username)){
                return new MessageInfo(true,admin,"登录成功");
            }else
                return new MessageInfo(false,"账号或密码错误");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"服务器错误,请稍后再试");
        }
    }

    public MessageInfo modifyPassword(String oldpassword,String newpassword){
        try{
            Admin admin = adminMapper.getAdmin();
           if( admin.getPassword().equals(oldpassword)) {
               adminMapper.modifyPassword(newpassword);
               return new MessageInfo(true,"密码修改成功");
           }
            return new MessageInfo(false,"原密码错误,请重新输入");


        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"服务器错误,请稍后再试");
        }
    }

}
