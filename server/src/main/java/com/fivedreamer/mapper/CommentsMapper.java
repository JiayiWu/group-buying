package com.fivedreamer.mapper;

import com.fivedreamer.model.Comment;

import java.util.List;


/**
 * Created by Jiayiwu on 17/4/29.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public interface CommentsMapper {


    public List<Comment> getCommentVO(int type, int orderid);
}
