package com.fivedreamer.service;

import com.fivedreamer.config.MessageInfo;
import com.fivedreamer.mapper.CarOrderMapper;
import com.fivedreamer.mapper.CommentsMapper;
import com.fivedreamer.mapper.ImgMapper;
import com.fivedreamer.mapper.MailOrderMapper;
import com.fivedreamer.model.CarOrder;
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
public class CarOrderService {

    @Resource
    CommentsMapper commentsMapper;

    @Resource
    ImgMapper imgMapper;

    @Resource
    CarOrderMapper carOrderMapper;


    public MessageInfo getAllDirection(){
        try {
            String [] result = carOrderMapper.getAllDirection();
            return new MessageInfo(true,result,"所有方向获取成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"服务器错误,订单获取失败");
        }
    }


    public MessageInfo getDirectionList(String direction){
        try {
            List<CarOrder> list = carOrderMapper.getCarOrderListByType(direction);
            List<CarOrderListVO> result = new LinkedList<CarOrderListVO>();
            if (list != null){
                for (CarOrder carOrder : list)
                    result.add(new CarOrderListVO(carOrder));
            }
            return new MessageInfo(true,result,"订单获取成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"服务器错误,订单获取失败");
        }
    }

    public MessageInfo getDirectionList(){
        String [] tem = (String [])getAllDirection().getObject();
        List<List<CarOrder>> result = new LinkedList<List<CarOrder>>();
        for (String tem1:tem){
            result.add((List<CarOrder>)getDirectionList(tem1).getObject());
        }
        return new MessageInfo(true,result,"获取成功");
    }


    public MessageInfo getCarOrderDetail(int id){
        try {
           CarOrder  carOrder  =  carOrderMapper.getCarOrderDetail(id);
            CarOrderDetailVO carOrderDetailVO = new CarOrderDetailVO(carOrder);

            String[]img = imgMapper.getImgUrl(0,id);
            List<Comment> comments = commentsMapper.getCommentVO(0,id);
            List<CommentVO> result = new LinkedList<CommentVO>();
            if (comments != null){
                for (Comment comment : comments)
                    result.add(new CommentVO(comment));
            }

            carOrderDetailVO.setComments(result);
            carOrderDetailVO.setImgPath(img);

            return new MessageInfo(true,carOrderDetailVO,"订单获取成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"服务器错误,订单获取失败");
        }
    }


    public MessageInfo addCarOrder(int id,String title,String content,String location,String direcrtion,String[] imgUrl){
        try {
            CarOrder carOrder = new CarOrder(id,title,content,location,System.currentTimeMillis(),direcrtion);
           carOrderMapper.addCarOrder(carOrder);
            if (imgUrl!=null) {
                for (String url : imgUrl) {
                    imgMapper.addImg(0, carOrder.getId(), url);
                }
            }
            return new MessageInfo(true,carOrder,"订单存储成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"服务器错误,订单获取失败");
        }
    }

    public MessageInfo modifyOrder(int id,String title,String content,String location,String direction,String[] imgUrl){
        try {
            imgMapper.deleteImg(0,id);
            carOrderMapper.modifyCarOrder(id,title,content,location,direction,System.currentTimeMillis());
            if (imgUrl != null) {
                for (String url : imgUrl) {
                    imgMapper.addImg(0, id, url);
                }
            }
            return new MessageInfo(true,"订单修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"服务器错误,订单获取失败");
        }
    }

    public MessageInfo getRecommendCarOrderList(){
        try {
            List<CarOrder> list = carOrderMapper.getCarRecommendList();
            List<CarOrderListVO> result = new LinkedList<CarOrderListVO>();
            for (CarOrder tem : list)
                    result.add(new CarOrderListVO(tem));

            return new MessageInfo(true,result,"订单推荐成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"服务器错误,订单获取失败");
        }
    }

    public MessageInfo successOrder(int id){
        try {
            carOrderMapper.successOrder(id);

            return new MessageInfo(true,"订单拼团成功");

        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"服务器错误,订单状态更改失败");
        }
    }

    public MessageInfo deleteOrder(int id){
        try {
            carOrderMapper.deleteOrder(id);

            return new MessageInfo(true,"订单删除成功");

        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"服务器错误,订单删除失败");
        }
    }

    public List<CarOrder> getSearch(String keyWord){
       return carOrderMapper.getSearch(keyWord);
    }
}
