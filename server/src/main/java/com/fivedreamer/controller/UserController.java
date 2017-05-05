package com.fivedreamer.controller;

import com.fivedreamer.config.MessageInfo;
import com.fivedreamer.model.Tag;
import com.fivedreamer.model.User;
import com.fivedreamer.service.RelationshipService;
import com.fivedreamer.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jiayiwu on 17/4/25.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
@Controller
public class UserController {

    @Resource
    UserService userService;

    @Resource
    RelationshipService relationshipService;

    /**
     * @param session
     * @return MessageInfo(True 则为获取当前用户信息成功,用户信息存储在object中,返回UserInfoVO.False 则为修改失败,具体失败原因存储在Reason中)
     */
    @RequestMapping("/user/info")
    @ResponseBody
    public MessageInfo getUserInfo(HttpSession session){
        return userService.getUserInfo(((User)session.getAttribute("user")).getId());
    }


    /**
     * @param id 用于查找指定用户的ID
     * @return MessageInfo(True 则为获取指定用户信息成功,用户信息存储在object中,返回UserInfoVO.False 则为修改失败,具体失败原因存储在Reason中)
     */
    @RequestMapping("/user/index/info")
    @ResponseBody
    public MessageInfo getDetailUserInfo(int id){

        return userService.getUserInfo(id);
    }

    /**
     * @param session
     * @return MessageInfo(True 则返回粉丝列表,用户信息存储在object中,返回List<UserListVO>.False 则为查找失败,具体失败原因存储在Reason中)
     */
    @RequestMapping("/user/fans/list")
    @ResponseBody
    public MessageInfo getFansList(HttpSession session){

        return userService.getFansList(((User)session.getAttribute("user")).getId());
    }
    /**
     * @param session
     * @return MessageInfo(True 则返回关注人列表,用户信息存储在object中,返回List<UserListVO>.False 则为查找失败,具体失败原因存储在Reason中)
     */
    @RequestMapping("/user/focus/list")
    @ResponseBody
    public MessageInfo getFocusList(HttpSession session){
        return userService.getFocusList(((User)session.getAttribute("user")).getId());
    }

    /**
     * 用于获取自己已经发布的订单消息
     * @param session
     * @return MessageInfo(True 则返回我发布的订单列表,用户信息存储在object中,返回List<GroupBuyOrderRecommendListVO>.False 则为查找失败,具体失败原因存储在Reason中)
     */
    @RequestMapping("/user/post/list")
    @ResponseBody
    public MessageInfo getSelfPostOrder(HttpSession session){

        return userService.getSelfPostOrder(((User)session.getAttribute("user")).getId());
    }

    /**
     * 用于获取自己已经拼到的订单消息
     * @param session
     * @return MessageInfo(True 则返回我拼到的订单列表,用户信息存储在object中,返回List<GroupBuyOrderRecommendListVO>.False 则为查找失败,具体失败原因存储在Reason中)
     */
    @RequestMapping("/user/group/list")
    @ResponseBody
    public MessageInfo getSelfGroupOrder(HttpSession session){
        return userService.getSelfGroupOrder(((User)session.getAttribute("user")).getId());
    }
    /**
     * 用于获取自己评价过的订单消息
     * @param session
     * @return MessageInfo(True 则返回我评价过的订单列表,用户信息存储在object中,返回List<GroupBuyOrderRecommendListVO>.False 则为查找失败,具体失败原因存储在Reason中)
     */
    @RequestMapping("/user/comments/list")
    @ResponseBody
    public MessageInfo getSelfCommentsOrder(HttpSession session){

        return userService.getSelfCommentsOrder(((User)session.getAttribute("user")).getId());
    }

    /**
     * @param session
     * @param nickName 昵称
     * @param school  学校
     * @param sex  性别
     * @param iconUrl 图像URL
     * @param backgroundUrl 背景图片URL
     * @param tag 标签
     * @return MessageInfo(True 则为修改成功,成功更新后的对象存储在object中,返回UserInfoVO. False 则为修改失败,具体失败原因存储在Reason中)
     */
    @RequestMapping("user/update/info")
    @ResponseBody
    public MessageInfo updateUserInfo(HttpSession session,String nickName,String school,String sex,String iconUrl,String backgroundUrl,String[] tag){

       int id = ((User)session.getAttribute("user")).getId();
      return userService.updateUserInfo(id,nickName,school,sex,iconUrl,backgroundUrl,tag);

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
        int id = ((User)session.getAttribute("user")).getId();
        return userService.updateUserPassword(id,oldPassword,newPassword);
    }

    /**
     * @param session
     * @param fansid 粉丝ID
     * @param model true为添加粉丝,false为删除粉丝
     * @return 返回添加成功的结果
     */
    @RequestMapping("/user/relationship/update")
    @ResponseBody
    public MessageInfo updateRelationship(HttpSession session,int fansid,boolean model){
        return relationshipService.updateRelationShip(((User)session.getAttribute("user")).getId(),fansid,model);
    }

    /**
     * @param session
     * @param fansid 粉丝ID
     * @return 返回MessageInfo的对象,判断结果存在Object中.如果是粉丝返回Ture,否则返回False
     */
    @RequestMapping("/user/relationship/judge")
    @ResponseBody
    public MessageInfo isFans(HttpSession session,int fansid){
        return relationshipService.isFans(((User)session.getAttribute("user")).getId(),fansid);
    }

    @RequestMapping("/user/info/id")
    @ResponseBody
    public MessageInfo getOwnerID(HttpSession session){
        Object user =  session.getAttribute("user");
        if (user != null){
            return new MessageInfo(true,((User)user).getId(),"ID获取成功");
        }
        return new MessageInfo(false,"请登录");
    }

    /**
     * 上传认证信息
     */
    @RequestMapping("/user/authentication/id")
    @ResponseBody
    public MessageInfo updateAuthentication(HttpSession session,String url){
            return userService.updateAuthentication(((User)session.getAttribute("user")).getId(),url);
    }

    /**
     * 获取用户是否认证
     */
    @RequestMapping("/user/authentication/index")
    @ResponseBody
    public MessageInfo isAuthentication(HttpSession session){
        return userService.isAuthentication(((User)session.getAttribute("user")).getId());
    }

    /**
     * 获取用户认证的URL
     */
    @RequestMapping("/user/authentication/url")
    @ResponseBody
    public MessageInfo getSIC_URL(HttpSession session){
        return userService.getSIC_URL(((User)session.getAttribute("user")).getId());
    }

}
