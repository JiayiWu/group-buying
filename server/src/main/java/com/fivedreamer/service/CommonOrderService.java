package com.fivedreamer.service;

import com.fivedreamer.config.MessageInfo;
import com.fivedreamer.mapper.BuyOrderMapper;
import com.fivedreamer.mapper.CarOrderMapper;
import com.fivedreamer.mapper.ClassOrderMapper;
import com.fivedreamer.mapper.MailOrderMapper;
import com.fivedreamer.model.CarOrder;
import com.fivedreamer.model.CommonOrder;
import com.fivedreamer.vo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jiayiwu on 17/5/2.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
@Service
public class CommonOrderService {

    @Resource
    CarOrderService carOrderService;

    @Resource
    ClassOrderService classOrderService;

    @Resource
    BuyOrderService buyOrderService;

    @Resource
    MailOrderService mailOrderService;


    public MessageInfo getSortRecommend() {

        MessageInfo tem1 = carOrderService.getRecommendCarOrderList();
        MessageInfo tem2 = classOrderService.getRecommendClassOrderList();
        MessageInfo tem3 = buyOrderService.getRecommendBuyOrderList();
        MessageInfo tem4 = mailOrderService.getRecommendMailOrderList();

        if (tem1.isResult() && tem2.isResult() && tem3.isResult() && tem4.isResult()) {
            MainSearchVO mainSearchVO = new MainSearchVO();
            mainSearchVO.setCarOrderListVOList((List<CarOrderListVO>) tem1.getObject());
            mainSearchVO.setBuyOrderListVOList((List<BuyOrderListVO>) tem3.getObject());
            mainSearchVO.setClassOrderListVOList((List<ClassOrderListVO>) tem2.getObject());
            mainSearchVO.setMailOrderListVOList((List<MailOrderListVO>) tem4.getObject());

            return new MessageInfo(true, mainSearchVO, "获取推荐成功");
        }

        return new MessageInfo(false, "获取推荐失败");
    }


    public MessageInfo search(String keyWord) {
        try {
            List<CarOrder> carOrder = carOrderService.getSearch(keyWord);
            List<CommonOrder> buyOrder = buyOrderService.getSearch(keyWord);
            List<CommonOrder> mailOrder = mailOrderService.getSearch(keyWord);
            List<CommonOrder> classOrder = classOrderService.getSearch(keyWord);
            List<GroupBuyOrderRecommendListVO> result = new LinkedList<GroupBuyOrderRecommendListVO>();

            for (CarOrder tem : carOrder)
                result.add(new CarOrderListVO(tem));

            for (CommonOrder tem : buyOrder)
                result.add(new BuyOrderListVO(tem));

            for (CommonOrder tem : mailOrder)
                result.add(new MailOrderListVO(tem));

            for (CommonOrder tem : classOrder)

                result.add(new ClassOrderListVO(tem));

            Collections.sort(result);
            return new MessageInfo(true, result, "数据获取成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"数据获取失败");
        }


    }

    public MessageInfo getRecommend() {
        try {
            List<CarOrderListVO> list1 = (List<CarOrderListVO>) carOrderService.getRecommendCarOrderList().getObject();
            List<BuyOrderListVO> list2 = (List<BuyOrderListVO>) buyOrderService.getRecommendBuyOrderList().getObject();
            List<ClassOrderListVO> list3 = (List<ClassOrderListVO>) classOrderService.getRecommendClassOrderList().getObject();
            List<MailOrderListVO> list4 = (List<MailOrderListVO>) mailOrderService.getRecommendMailOrderList().getObject();

            List<GroupBuyOrderRecommendListVO> result = new LinkedList<GroupBuyOrderRecommendListVO>();

            for (CarOrderListVO tem : list1) {
                result.add(tem);
            }


            for (BuyOrderListVO tem : list2) {
                result.add(tem);
            }


            for (ClassOrderListVO tem : list3) {
                result.add(tem);
            }


            for (MailOrderListVO tem : list4) {
                result.add(tem);
            }
            Collections.sort(result);
            return new MessageInfo(true, result, "推荐成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageInfo(false, "服务器错误,推荐错误");
        }


    }

}
