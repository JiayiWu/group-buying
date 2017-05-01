package com.fivedreamer.mapper;

import com.fivedreamer.model.Comment;
import com.fivedreamer.model.CommentPO;
import com.fivedreamer.model.Relationship;


import java.util.List;


/**
 * Created by Jiayiwu on 17/4/29.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public interface RelationshipMapper {


    public int addRelationship(int ownerid, int friendid);

    public int removeRelationship(int ownerid, int friendid);

    public Relationship getRelationship(int ownerid, int friendid);

}
