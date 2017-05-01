package com.fivedreamer.service;

import com.fivedreamer.config.MessageInfo;
import com.fivedreamer.mapper.ClassOrderMapper;
import com.fivedreamer.mapper.CommentsMapper;
import com.fivedreamer.mapper.ImgMapper;
import com.fivedreamer.model.Comment;
import com.fivedreamer.model.CommonOrder;
import com.fivedreamer.vo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jiayiwu on 17/5/1.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
@Service
public class ClassOrderService {

    @Resource
    CommentsMapper commentsMapper;

    @Resource
    ImgMapper imgMapper;

    @Resource
    ClassOrderMapper classOrderMapper;


    public MessageInfo getTypeList(int type){
        try {
            List<CommonOrder> list = classOrderMapper.getClassOrderListByType(type);
            List<ClassOrderListVO> result = new LinkedList<ClassOrderListVO>();
            if (list != null){
                for (CommonOrder commonOrder : list)
                    result.add(new ClassOrderListVO(commonOrder));
            }
            return new MessageInfo(true,result,"订单获取成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"服务器错误,订单获取失败");
        }
    }


    public MessageInfo getClassOrderDetail(int id){
        try {
           CommonOrder commonOrder =  classOrderMapper.getClassOrderDetail(id);
            ClassOrderDetailVO classOrderDetailVO = new ClassOrderDetailVO(commonOrder);

            String[]img = imgMapper.getImgUrl(1,id);
            List<Comment> comments = commentsMapper.getCommentVO(1,id);
            List<CommentVO> result = new LinkedList<CommentVO>();
            if (comments != null){
                for (Comment comment : comments)
                    result.add(new CommentVO(comment));
            }

            classOrderDetailVO.setComments(result);
            classOrderDetailVO.setImgPath(img);

            return new MessageInfo(true,classOrderDetailVO,"订单获取成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"服务器错误,订单获取失败");
        }
    }


    public MessageInfo addClassOrder(int id,String title,String content,String location,int type,String[] imgUrl){
        try {
            CommonOrder commonOrder = new CommonOrder(id,title,content,location,System.currentTimeMillis(),type);
           classOrderMapper.addClassOrder(commonOrder);
            if (imgUrl!=null) {
                for (String url : imgUrl) {
                    imgMapper.addImg(1, commonOrder.getId(), url);
                }
            }
            return new MessageInfo(true,commonOrder,"订单存储成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"服务器错误,订单获取失败");
        }
    }

    public MessageInfo modifyOrder(int id,String title,String content,String location,int type,String[] imgUrl){
        try {
            imgMapper.deleteImg(1,id);
            classOrderMapper.modifyClassOrder(id,title,content,location,type,System.currentTimeMillis());
            if (imgUrl != null) {
                for (String url : imgUrl) {
                    imgMapper.addImg(1, id, url);
                }
            }
            return new MessageInfo(true,"订单修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"服务器错误,订单获取失败");
        }
    }

    public MessageInfo getRecommendClassOrderList(){
        try {
            List<CommonOrder> list = classOrderMapper.getClassRecommendList();
            List<ClassOrderListVO> result = new LinkedList<ClassOrderListVO>();
            for (CommonOrder tem : list)
                    result.add(new ClassOrderListVO(tem));

            return new MessageInfo(true,result,"订单推荐成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"服务器错误,订单获取失败");
        }
    }

    public MessageInfo successOrder(int id){
        try {
            classOrderMapper.successOrder(id);
            return new MessageInfo(true,"订单拼团成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"服务器错误,订单状态更改失败");
        }
    }

    public MessageInfo deleteOrder(int id){
        try {
            classOrderMapper.deleteOrder(id);
            return new MessageInfo(true,"订单删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"服务器错误,订单删除失败");
        }
    }
}
