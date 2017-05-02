package com.fivedreamer.controller;

import com.fivedreamer.config.MessageInfo;
import com.fivedreamer.model.User;
import com.fivedreamer.service.MailOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by Jiayiwu on 17/4/26.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
@Controller
public class MailOrderController {

    @Resource
    MailOrderService mailOrderService;




    /**
     * @param type 0日本 1韩国 2美国 3欧洲 4澳洲 5港澳台泰 6其他地区 7网店拼邮
     * @return MessageInfo (true 表示返回该方向的订单成功,Object为List<MailOrderListVO>类型,存储的在该类型上的订单. False表示返回失败,失败原因存储在Reason中)
     */
    @RequestMapping("/mail/type/index")
    @ResponseBody
    public MessageInfo getTypeList(int type){

        return mailOrderService.getTypeList(type);
    }


    @RequestMapping("/mail/order/index")
    @ResponseBody
    /**
     * @param id 订单编号
     * @return MessageInfo (true 表示返回具体的订单成功,Object为MailOrderDetailVO类型,存储的该订单的详细信息. False表示返回失败,失败原因存储在Reason中)
     */
    public MessageInfo getMailOrderDetail(int id){

        return mailOrderService.getMailOrderDetail(id);
    }

    @RequestMapping("/mail/order/add")
    @ResponseBody
    /**
     * @param session
     * @param title 标题
     * @param content 拼单内容
     * @param location 地址
     * @param type 0日本 1韩国 2美国 3欧洲 4澳洲 5港澳台泰 6其他地区 7网店拼邮
     * @param imgUrl 发布图片的URL
     * @return MessageInfo (true 表示添加订单成功. False表示添加失败,失败原因存储在Reason中)
     */
    public MessageInfo addMailOrder(HttpSession session,String title,String content,String location,int type,String imgUrl){
       return mailOrderService.addMailOrder(((User)session.getAttribute("user")).getId(),title,content,location,type,imgUrl.split(" "));
    }

    @RequestMapping("/mail/order/modify")
    @ResponseBody
    /**
     * @param id 要修改帖子的ID
     * @param title 标题
     * @param content 拼单内容
     * @param location 地址
     * @param type 0日本 1韩国 2美国 3欧洲 4澳洲 5港澳台泰 6其他地区 7网店拼邮
     * @param imgUrl 发布图片的URL
     * @return MessageInfo (true 表示修改订单成功. False表示修改失败,失败原因存储在Reason中)
     */
    public MessageInfo modifyOrder(int id,String title,String content,String location,int type,String imgUrl){
        return mailOrderService.modifyOrder(id,title,content,location,type,imgUrl.split(" "));
    }

    @RequestMapping("/mail/order/list")
    @ResponseBody
    /**
     * @return MessageInfo (true 表示返回成功.返回的Object对象为List<MailOrderListVO>,根据发布时间排序后返回该对象. False表示返回失败,失败原因存储在Reason中)
     */
    public MessageInfo getRecommendMailOrderList(){
        return mailOrderService.getRecommendMailOrderList();
    }


    @RequestMapping("/mail/order/success")
    @ResponseBody
    /**
     * @param id 订单编号
     * @return MessageInfo (true 表示更改订单状态成功,订单变为拼团成功. False表示状态改变失败,失败原因存储在Reason中)
     */
    public MessageInfo successOrder(int id){
        return mailOrderService.successOrder(id);
    }

    @RequestMapping("/mail/order/delete")
    @ResponseBody
    /**
     * @param id 订单编号
     * @return MessageInfo (true 表示更改订单状态成功,订单被删除. False表示状态改变失败,失败原因存储在Reason中)
     */
    public MessageInfo deleteOrder(int id){
        return mailOrderService.deleteOrder(id);
    }
}
