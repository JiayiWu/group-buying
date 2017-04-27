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
public class UserController {


    /**
     * @param session
     * @return MessageInfo(True 则为获取当前用户信息成功,用户信息存储在object中,返回UserInfoVO.False 则为修改失败,具体失败原因存储在Reason中)
     */
    @RequestMapping("/user/info")
    @ResponseBody
    public MessageInfo getUserInfo(HttpSession session){
        return null;
    }


    /**
     * @param id 用于查找指定用户的ID
     * @return MessageInfo(True 则为获取指定用户信息成功,用户信息存储在object中,返回UserInfoVO.False 则为修改失败,具体失败原因存储在Reason中)
     */
    @RequestMapping("/user/index/info")
    @ResponseBody
    public MessageInfo getDetailUserInfo(int id){
        return null;
    }

    /**
     * @return MessageInfo(True 则返回粉丝列表,用户信息存储在object中,返回List<UserListVO>.False 则为查找失败,具体失败原因存储在Reason中)
     */
    @RequestMapping("/user/fans/list")
    @ResponseBody
    public MessageInfo getFansList(){
        return null;
    }
    /**
     * @return MessageInfo(True 则返回关注人列表,用户信息存储在object中,返回List<UserListVO>.False 则为查找失败,具体失败原因存储在Reason中)
     */
    @RequestMapping("/user/focus/list")
    @ResponseBody
    public MessageInfo getFocusList(){
        return null;
    }

    /**
     * 用于获取自己已经发布的订单消息
     * @return MessageInfo(True 则返回我发布的订单列表,用户信息存储在object中,返回List<GroupBuyOrderRecommendListVO>.False 则为查找失败,具体失败原因存储在Reason中)
     */
    @RequestMapping("/user/post/list")
    @ResponseBody
    public MessageInfo getSelfPostOrder(){
        return null;
    }

    /**
     * 用于获取自己已经拼到的订单消息
     * @return MessageInfo(True 则返回我拼到的订单列表,用户信息存储在object中,返回List<GroupBuyOrderRecommendListVO>.False 则为查找失败,具体失败原因存储在Reason中)
     */
    @RequestMapping("/user/group/list")
    @ResponseBody
    public MessageInfo getSelfGroupOrder(){
        return null;
    }
    /**
     * 用于获取自己评价过的订单消息
     * @return MessageInfo(True 则返回我评价过的订单列表,用户信息存储在object中,返回List<GroupBuyOrderRecommendListVO>.False 则为查找失败,具体失败原因存储在Reason中)
     */
    @RequestMapping("/user/comments/list")
    @ResponseBody
    public MessageInfo getSelfCommentsOrder(){
        return null;
    }

    /**
     * @param session
     * @param nickName 昵称
     * @param school  学校
     * @param sex  性别
     * @param iconUrl 图像URL
     * @param backgroundUrl 背景图片URL
     * @return MessageInfo(True 则为修改成功,成功更新后的对象存储在object中,返回UserInfoVO. False 则为修改失败,具体失败原因存储在Reason中)
     */
    @RequestMapping("user/update/info")
    @ResponseBody
    public MessageInfo updateUserInfo(HttpSession session,String nickName,String school,String sex,String iconUrl,String backgroundUrl){
        return null;
    }

    /**
     * @param session
     * @param oldPassword 原来密码
     * @param newPassword 新密码
     * @return MessageInfo(True 则为修改成功,不返回任何对象,object为null.  False 则为修改失败,具体失败原因存储在Reason中,成功更新后的对心存储在object中)
     */
    @RequestMapping("user/update/password")
    @ResponseBody
    public MessageInfo updateUserPassword(HttpSession session,String oldPassword,String newPassword){
        return null;
    }




}
