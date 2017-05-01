package com.fivedreamer.service;

import com.fivedreamer.config.MessageInfo;
import com.fivedreamer.mapper.BuyOrderMapper;
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
public class BuyOrderService {

    @Resource
    CommentsMapper commentsMapper;

    @Resource
    ImgMapper imgMapper;

    @Resource
    BuyOrderMapper buyOrderMapper;


    public MessageInfo getTypeList(int type){
        try {
            List<CommonOrder> list = buyOrderMapper.getBuyOrderListByType(type);
            List<BuyOrderListVO> result = new LinkedList<BuyOrderListVO>();
            if (list != null){
                for (CommonOrder commonOrder : list)
                    result.add(new BuyOrderListVO(commonOrder));
            }
            return new MessageInfo(true,result,"订单获取成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"服务器错误,订单获取失败");
        }
    }


    public MessageInfo getBuyOrderDetail(int id){
        try {
           CommonOrder commonOrder =  buyOrderMapper.getBuyOrderDetail(id);
            BuyOrderDetailVO buyOrderDetailVO = new BuyOrderDetailVO(commonOrder);

            String[]img = imgMapper.getImgUrl(3,id);
            List<Comment> comments = commentsMapper.getCommentVO(3,id);
            List<CommentVO> result = new LinkedList<CommentVO>();
            if (comments != null){
                for (Comment comment : comments)
                    result.add(new CommentVO(comment));
            }

            buyOrderDetailVO.setComments(result);
            buyOrderDetailVO.setImgPath(img);

            return new MessageInfo(true,buyOrderDetailVO,"订单获取成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"服务器错误,订单获取失败");
        }
    }


    public MessageInfo addBuyOrder(int id,String title,String content,String location,int type,String[] imgUrl){
        try {
            CommonOrder commonOrder = new CommonOrder(id,title,content,location,System.currentTimeMillis(),type);
           buyOrderMapper.addBuyOrder(commonOrder);
            if (imgUrl!=null) {
                for (String url : imgUrl) {
                    imgMapper.addImg(3, commonOrder.getId(), url);
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
            imgMapper.deleteImg(3,id);
            buyOrderMapper.modifyBuyOrder(id,title,content,location,type,System.currentTimeMillis());
            if (imgUrl != null) {
                for (String url : imgUrl) {
                    imgMapper.addImg(3, id, url);
                }
            }
            return new MessageInfo(true,"订单修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"服务器错误,订单获取失败");
        }
    }

    public MessageInfo getRecommendBuyOrderList(){
        try {
            List<CommonOrder> list = buyOrderMapper.getBuyRecommendList();
            List<BuyOrderListVO> result = new LinkedList<BuyOrderListVO>();
            for (CommonOrder tem : list)
                    result.add(new BuyOrderListVO(tem));

            return new MessageInfo(true,result,"订单推荐成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"服务器错误,订单获取失败");
        }
    }

    public MessageInfo successOrder(int id){
        try {
            buyOrderMapper.successOrder(id);
            return new MessageInfo(true,"订单拼团成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"服务器错误,订单状态更改失败");
        }
    }

    public MessageInfo deleteOrder(int id){
        try {
            buyOrderMapper.deleteOrder(id);
            return new MessageInfo(true,"订单删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"服务器错误,订单删除失败");
        }
    }
}
