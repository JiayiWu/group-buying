package com.fivedreamer.controller;

import com.fivedreamer.config.MessageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Jiayiwu on 17/4/26.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
@Controller
public class CommentsController {


    /**
     * @param session
     * @param orderid 被评论订单ID
     * @param orderType 被评论订单类型 0 拼车 1是拼课 2是拼邮 3是拼团
     * @param content 评论内容
     * @return MessageInfo(True表示回复成功. False表示回复失败,失败原因存储在Reason中)
     */
    @RequestMapping("/message/leave")
    @ResponseBody
    public MessageInfo leaveMessage(HttpSession session,String orderid,int orderType,String content){
        return null;
    }

    /**
     * @param session
     * @param orderid 被评论订单ID
     * @param orderType 被评论订单类型 0 拼车 1是拼课 2是拼邮 3是拼团
     * @param content 回复内容
     * @param replyid 被回复人的ID
     * @return MessageInfo(True表示回复成功. False表示回复失败,失败原因存储在Reason中)
     */
    @RequestMapping("/message/reply")
    @ResponseBody
    public MessageInfo replyMessage(HttpSession session,String orderid,int orderType,String content,int replyid){
        return null;
    }

    /**
     * @param id 要被删除的评论或者回复的ID
     * @return MessageInfo(True表示删除成功. False表示删除失败,失败原因存储在Reason中)
     */
    @RequestMapping("/message/delete")
    @ResponseBody
    public MessageInfo deleteMessage(int id){
        return null;
    }
}
