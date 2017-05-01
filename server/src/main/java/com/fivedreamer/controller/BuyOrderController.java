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
public class BuyOrderController {



    /**
     * @param type 0吃喝 1玩乐 2服饰 3其他
     * @return MessageInfo (true 表示返回该方向的订单成功,Object为List<BuyOrderListVO>类型,存储的在该类型上的订单. False表示返回失败,失败原因存储在Reason中)
     */
    @RequestMapping("/buy/type/index")
    @ResponseBody
    public MessageInfo getTypeList(int type){
        return null;
    }




    @RequestMapping("/buy/order/index")
    @ResponseBody
    /**
     * @param id 订单编号
     * @return MessageInfo (true 表示返回具体的订单成功,Object为BuyOrderDetailVO类型,存储的该订单的详细信息. False表示返回失败,失败原因存储在Reason中)
     */
    public MessageInfo getBuyOrderDetail(int id){
        return null;
    }

    @RequestMapping("/buy/order/add")
    @ResponseBody
    /**
     * @param session
     * @param title 标题
     * @param content 拼单内容
     * @param location 地址
     * @param type 0吃喝 1玩乐 2服饰 3其他
     * @param imgUrl 发布图片的URL
     * @return MessageInfo (true 表示添加订单成功. False表示添加失败,失败原因存储在Reason中)
     */
    public MessageInfo addBuyOrder(HttpSession session,String title,String content,String location,int type,String[] imgUrl){
        return null;
    }


    @RequestMapping("/buy/order/modify")
    @ResponseBody
    /**
     * @param id 要修改帖子的ID
     * @param title 标题
     * @param content 拼单内容
     * @param location 地址
     * @param type 0吃喝 1玩乐 2服饰 3其他
     * @param imgUrl 发布图片的URL
     * @return MessageInfo (true 表示修改订单成功. False表示修改失败,失败原因存储在Reason中)
     */
    public MessageInfo modifyOrder(int id,String title,String content,String location,int type,String[] imgUrl){
        return null;
    }

    @RequestMapping("/buy/order/list")
    @ResponseBody
    /**
     * @return MessageInfo (true 表示返回成功.返回的Object对象为List<BuyOrderListVO>,根据发布时间排序后返回该对象. False表示返回失败,失败原因存储在Reason中)
     */
    public MessageInfo getRecommendBuyOrderList(){
        return null;
    }


    @RequestMapping("/buy/order/success")
    @ResponseBody
    /**
     * @param id 订单编号
     * @return MessageInfo (true 表示更改订单状态成功,订单变为拼团成功. False表示状态改变失败,失败原因存储在Reason中)
     */
    public MessageInfo successOrder(int id){
        return null;
    }

    @RequestMapping("/buy/order/delete")
    @ResponseBody
    /**
     * @param id 订单编号
     * @return MessageInfo (true 表示更改订单状态成功,订单被删除. False表示状态改变失败,失败原因存储在Reason中)
     */
    public MessageInfo deleteOrder(int id){
        return null;
    }
}
