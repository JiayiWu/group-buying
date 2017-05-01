package com.fivedreamer.mapper;

import com.fivedreamer.model.Comment;
import com.fivedreamer.model.CommentPO;

import java.util.List;


/**
 * Created by Jiayiwu on 17/4/29.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public interface CommentsMapper {


    public List<Comment> getCommentVO(int type, int orderid);

    public CommentPO getDetailComment(int id);

    public int leaveMessage(CommentPO comment);

    public int replyMessage(CommentPO comment);

    public int deleteMessage(int id);
}
