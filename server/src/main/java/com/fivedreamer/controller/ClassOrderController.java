package com.fivedreamer.controller;

import com.fivedreamer.config.MessageInfo;
import com.fivedreamer.mapper.ClassOrderMapper;
import com.fivedreamer.model.User;
import com.fivedreamer.service.ClassOrderService;
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
public class ClassOrderController {


    @Resource
    ClassOrderService classOrderService;



    /**
     * @param type 0雅思托福 1BEC托业 2考研 3考证 4小语种 5考驾照 6来健身 7其他
     * @return MessageInfo (true 表示返回该方向的订单成功,Object为List<ClassOrderDetailVO>类型,存储的在该类型上的订单. False表示返回失败,失败原因存储在Reason中)
     */
    @RequestMapping("/class/type/index")
    @ResponseBody
    public MessageInfo getTypeList(int type){
        return classOrderService.getTypeList(type);
    }


    @RequestMapping("/class/order/index")
    @ResponseBody
    /**
     * @param id 订单编号
     * @return MessageInfo (true 表示返回具体的订单成功,Object为ClassOrderDetailVO类型,存储的该订单的详细信息. False表示返回失败,失败原因存储在Reason中)
     */
    public MessageInfo getClassOrderDetail(int id){
        return classOrderService.getClassOrderDetail(id);
    }





    @RequestMapping("/class/order/add")
    @ResponseBody
    /**
     * @param session
     * @param title 标题
     * @param content 拼单内容
     * @param location 地址
     * @param type 0雅思托福 1BEC托业 2考研 3考证 4小语种 5考驾照 6来健身 7其他
     * @param imgUrl 发布图片的URL
     * @return MessageInfo (true 表示添加订单成功. False表示添加失败,失败原因存储在Reason中)
     */
    public MessageInfo addClassOrder(HttpSession session,String title,String content,String location,int type,String[] imgUrl){
        return classOrderService.addClassOrder(((User)session.getAttribute("user")).getId(),title,content,location,type,imgUrl);
    }

    @RequestMapping("/class/order/modify")
    @ResponseBody
    /**
     * @param id 要修改帖子的ID
     * @param title 标题
     * @param content 拼单内容
     * @param location 地址
     * @param type 0雅思托福 1BEC托业 2考研 3考证 4小语种 5考驾照 6来健身 7其他
     * @param imgUrl 发布图片的URL
     * @return MessageInfo (true 表示修改订单成功. False表示修改失败,失败原因存储在Reason中)
     */
    public MessageInfo modifyOrder(int id,String title,String content,String location,int type,String[] imgUrl){
        return classOrderService.modifyOrder(id,title,content,location,type,imgUrl);
    }

    @RequestMapping("/class/order/list")
    @ResponseBody
    /**
     * @return MessageInfo (true 表示返回成功.返回的Object对象为List<ClassOrderListVO>,根据发布时间排序后返回该对象. False表示返回失败,失败原因存储在Reason中)
     */
    public MessageInfo getRecommendClassOrderList(){
        return classOrderService.getRecommendClassOrderList();
    }


    @RequestMapping("/class/order/success")
    @ResponseBody
    /**
     * @param id 订单编号
     * @return MessageInfo (true 表示更改订单状态成功,订单变为拼团成功. False表示状态改变失败,失败原因存储在Reason中)
     */
    public MessageInfo successOrder(int id){
        return classOrderService.successOrder(id);
    }

    @RequestMapping("/class/order/delete")
    @ResponseBody
    /**
     * @param id 订单编号
     * @return MessageInfo (true 表示更改订单状态成功,订单被删除. False表示状态改变失败,失败原因存储在Reason中)
     */
    public MessageInfo deleteOrder(int id){
        return classOrderService.deleteOrder(id);
    }
}
