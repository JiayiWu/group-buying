package com.fivedreamer.service;

import com.fivedreamer.config.MessageInfo;
import com.fivedreamer.mapper.*;
import com.fivedreamer.model.CommentPO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Jiayiwu on 17/5/1.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
@Service
public class CommentsService {

    @Resource
    ClassOrderMapper classOrderMapper;
    @Resource
    MailOrderMapper mailOrderMapper;
    @Resource
    CarOrderMapper carOrderMapper;
    @Resource
    BuyOrderMapper buyOrderMapper;

    @Resource
    CommentsMapper commentsMapper;

    public MessageInfo leaveMessage(int id,int orderid,int orderType,String content){
        try{
            CommentPO commentPO = new CommentPO(id,orderType,orderid,content,System.currentTimeMillis());
            commentsMapper.leaveMessage(commentPO);
            ascMessageCount(orderType,orderid);
            return new MessageInfo(true,commentPO,"评论成功") ;
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"服务器错误,评论失败");
        }

    }

    public MessageInfo replyMessage(int id,int orderid,int orderType,String content,int replyid){
        try{
            CommentPO commentPO = new CommentPO(id,orderid,orderType,orderid,content,System.currentTimeMillis());
            commentsMapper.leaveMessage(commentPO);
            ascMessageCount(orderType,orderid);
            return new MessageInfo(true,commentPO,"评论成功") ;
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"服务器错误,评论失败");
        }

    }

    public MessageInfo deleteMessage(int id){
        try{
            CommentPO commentPO = commentsMapper.getDetailComment(id);
            if (commentPO != null){
                commentsMapper.deleteMessage(id);
                switch (commentPO.getType()){
                    case 0:
                        carOrderMapper.descMessageCount(commentPO.getOrderid());
                        break;
                    case 1:
                        classOrderMapper.descMessageCount(commentPO.getOrderid());
                        break;
                    case 2:
                        mailOrderMapper.descMessageCount(commentPO.getOrderid());
                        break;
                    case 3:
                        buyOrderMapper.descMessageCount(commentPO.getOrderid());
                        break;
                }

                return new MessageInfo(true,"删除成功") ;
            }

        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"服务器错误,评论失败");
        }

        return new MessageInfo(false,"留言ID不存在");
    }

    private void ascMessageCount(int orderType,int orderid) throws Exception{
        switch (orderType){
            case 0:
                carOrderMapper.ascMessageCount(orderid);
                break;
            case 1:
                classOrderMapper.ascMessageCount(orderid);
                break;
            case 2:
                mailOrderMapper.ascMessageCount(orderid);
                break;
            case 3:
                buyOrderMapper.ascMessageCount(orderid);
                break;
        }
    }

}
