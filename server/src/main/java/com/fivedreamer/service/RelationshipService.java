package com.fivedreamer.service;

import com.fivedreamer.config.MessageInfo;
import com.fivedreamer.mapper.*;
import com.fivedreamer.model.CommentPO;
import com.fivedreamer.model.Relationship;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * Created by Jiayiwu on 17/5/1.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
@Service
public class RelationshipService {

    @Resource
    RelationshipMapper relationshipMapper;

    public MessageInfo updateRelationShip(int ownerid,int fansid,boolean model){
       try {
           if (model) {
                relationshipMapper.addRelationship(ownerid,fansid);
           } else {
                relationshipMapper.removeRelationship(ownerid,fansid);
           }
           return new MessageInfo(true,"对象关系更新成功");
       }catch (Exception e){
           e.printStackTrace();
           return new MessageInfo(false,"服务器错误,请稍后重试");
       }
    }

    public MessageInfo isFans(int ownerid,int fansid){
        try{

           Relationship relationship = relationshipMapper.getRelationship(ownerid,fansid);
            if (relationship == null){
                return new MessageInfo(true,false,"对象关系获取成功");
            }else
                return new MessageInfo(true,true,"对象关系获取成功");

        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"服务器错误");
        }

    }
}
