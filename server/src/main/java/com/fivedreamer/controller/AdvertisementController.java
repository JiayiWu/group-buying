package com.fivedreamer.controller;

import com.fivedreamer.config.MessageInfo;
import com.fivedreamer.service.AdvertisementService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Jiayiwu on 17/4/29.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
@Controller
public class AdvertisementController {


    @Resource
    AdvertisementService advertisementService;

    /**
     * @param imgURL  广告图片URL
     * @param contents 广告文本内容
     * @param targetURL 跳转URL
     * @return MessageInfo(True 则为添加广告成功,成功后该广告ID放在Object中. False 则为添加失败,具体失败原因存储在Reason中)
     */
    @RequestMapping("/advertisement/add")
    @ResponseBody
    public MessageInfo addAdvertisement(String imgURL,String contents,String targetURL){

        return advertisementService.addAdvertisement(imgURL,contents,targetURL);
    }


    /**
     * @param id 广告ID
     * @param imgURL  广告图片URL
     * @param contents 广告文本内容
     * @param targetURL 跳转URL
     * @return MessageInfo(True 则为广告修改成功,修改成功的对象放在 Object中.对象类型是Advertisement. False 则为修改失败,具体失败原因存储在Reason中)
     */
    @RequestMapping("/advertisement/update")
    @ResponseBody
    public MessageInfo updateAdvertisement(int id,String imgURL,String contents,String targetURL){
        return advertisementService.updateAdvertisement(id,imgURL,contents,targetURL);
    }


    /**
     * @param id 要删除的广告ID
     * @return MessageInfo(True 则为广告删除成功,False 为广告删除失败)
     */
    @RequestMapping("/advertisement/delete")
    @ResponseBody
    public MessageInfo deleteAdvertisement(int id){
        return advertisementService.deleteAdvertisement(id);
    }

    /**
     * @return MessageInfo(True 则为广告推荐获取成功,成功推荐后的对象放在 Object中.对象类型是List<Advertisement>,False 为广告推荐获取失败)
     */
    @RequestMapping("/advertisement/list")
    @ResponseBody
    public MessageInfo getAdvertisementList(){
        return advertisementService.getAdvertisementList();
    }
}
