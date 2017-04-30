package com.fivedreamer.vo;

import com.fivedreamer.model.CarOrder;
import com.fivedreamer.utils.DataUtil;

/**
 * Created by Jiayiwu on 17/4/26.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public class CarOrderListVO extends GroupBuyOrderRecommendListVO {


    public CarOrderListVO() {

    }

    public CarOrderListVO(CarOrder carOrder){
        this.id = carOrder.getId();
        this.ordertype = 0;
        this.username = carOrder.getUsername();
        this.usericonurl = carOrder.getUsericonurl();
        this.title = carOrder.getTitle();
        this.content = carOrder.getContent();
        this.location = carOrder.getLocation();
        this.time = carOrder.getTime();
        this.timeformate = DataUtil.getLongToDateString(carOrder.getTime());
        this.type = carOrder.getDirection();
        this.leaveMessageCount = carOrder.getMessageCount();
    }
}
