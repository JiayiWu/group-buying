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
public class CommonOrderController {


    /**
     * @return MessageInfo (true 表示推荐成功,把MainSearchVO存储在Object中返回. False表示推荐失败,失败原因存储在Reason中)
     */
    @RequestMapping("/index/sort/recommend")
    @ResponseBody
    public MessageInfo getSortRecommend(){
        return null;
    }

    /**
     * @param search 需要检索的信息
     * @return MessageInfo (true 表示检索成功,把MainSearchVO存储在Object中返回. False表示检索失败,失败原因存储在Reason中)
     */
    @RequestMapping("/index/search")
    @ResponseBody
    public MessageInfo search(String search){
        return null;
    }


    /**
     * @return MessageInfo (true 表示推荐成功,把GroupBuyOrderRecommendListVO存储在Object中返回. False表示推荐失败,失败原因存储在Reason中)
     */
    @RequestMapping("/index/recommend")
    @ResponseBody
    public MessageInfo getRecommend(){
        return null;
    }





}
