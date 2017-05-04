package com.fivedreamer.service;

import com.fivedreamer.config.MessageInfo;
import com.fivedreamer.mapper.CommentsMapper;
import com.fivedreamer.mapper.ImgMapper;
import com.fivedreamer.mapper.MailOrderMapper;
import com.fivedreamer.model.Comment;
import com.fivedreamer.model.CommonOrder;
import com.fivedreamer.vo.CommentVO;
import com.fivedreamer.vo.MailOrderDetailVO;
import com.fivedreamer.vo.MailOrderListVO;
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
public class MailOrderService {

    @Resource
    CommentsMapper commentsMapper;

    @Resource
    ImgMapper imgMapper;

    @Resource
    MailOrderMapper mailOrderMapper;


    public MessageInfo getTypeList(int type){
        try {
            List<CommonOrder> list = mailOrderMapper.getMailOrderListByType(type);
            List<MailOrderListVO> result = new LinkedList<MailOrderListVO>();
            if (list != null){
                for (CommonOrder commonOrder : list)
                    result.add(new MailOrderListVO(commonOrder));
            }
            return new MessageInfo(true,result,"订单获取成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"服务器错误,订单获取失败");
        }
    }

    public MessageInfo getTypeList(){
        List<List<MailOrderListVO>> result = new LinkedList<List<MailOrderListVO>>();
        for (int i=0;i<8;i++){
            result.add((List<MailOrderListVO>)getTypeList(i).getObject());
        }
        return new MessageInfo(true,result,"获取成功");
    }


    public MessageInfo getMailOrderDetail(int id){
        try {
           CommonOrder commonOrder =  mailOrderMapper.getMailOrderDetail(id);
            MailOrderDetailVO mailOrderDetailVO = new MailOrderDetailVO(commonOrder);

            String[]img = imgMapper.getImgUrl(2,id);
            List<Comment> comments = commentsMapper.getCommentVO(2,id);
            List<CommentVO> result = new LinkedList<CommentVO>();
            if (comments != null){
                for (Comment comment : comments)
                    result.add(new CommentVO(comment));
            }

            mailOrderDetailVO.setComments(result);
            mailOrderDetailVO.setImgPath(img);

            return new MessageInfo(true,mailOrderDetailVO,"订单获取成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"服务器错误,订单获取失败");
        }
    }


    public MessageInfo addMailOrder(int id,String title,String content,String location,int type,String[] imgUrl){
        try {
            CommonOrder commonOrder = new CommonOrder(id,title,content,location,System.currentTimeMillis(),type);
           mailOrderMapper.addMailOrder(commonOrder);
            if (imgUrl!=null) {
                for (String url : imgUrl) {
                    imgMapper.addImg(2, commonOrder.getId(), url);
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
            imgMapper.deleteImg(2,id);
            mailOrderMapper.modifyMailOrder(id,title,content,location,type,System.currentTimeMillis());
            if (imgUrl != null) {
                for (String url : imgUrl) {
                    imgMapper.addImg(2, id, url);
                }
            }
            return new MessageInfo(true,"订单修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"服务器错误,订单获取失败");
        }
    }

    public MessageInfo getRecommendMailOrderList(){
        try {
            List<CommonOrder> list = mailOrderMapper.getMailRecommendList();
            List<MailOrderListVO> result = new LinkedList<MailOrderListVO>();
            for (CommonOrder tem : list)
                    result.add(new MailOrderListVO(tem));

            return new MessageInfo(true,result,"订单推荐成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"服务器错误,订单获取失败");
        }
    }

    public MessageInfo successOrder(int id){
        try {
            mailOrderMapper.successOrder(id);
            return new MessageInfo(true,"订单拼团成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"服务器错误,订单状态更改失败");
        }
    }

    public MessageInfo deleteOrder(int id){
        try {
            mailOrderMapper.deleteOrder(id);
            return new MessageInfo(true,"订单删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"服务器错误,订单删除失败");
        }
    }

    public List<CommonOrder> getSearch(String keyWord){
        return mailOrderMapper.getSearch(keyWord);
    }
}
