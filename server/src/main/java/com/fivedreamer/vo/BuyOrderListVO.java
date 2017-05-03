package com.fivedreamer.vo;

import com.fivedreamer.model.CommonOrder;
import com.fivedreamer.utils.DataUtil;

/**
 * Created by Jiayiwu on 17/4/26.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public class BuyOrderListVO extends GroupBuyOrderRecommendListVO {


    public BuyOrderListVO() {
    }

    public BuyOrderListVO(CommonOrder commonOrder) {
        this.userid = commonOrder.getUserid();
        this.id = commonOrder.getId();
        this.ordertype = 3;
        this.username = commonOrder.getUsername();
        this.usericonurl = commonOrder.getUsericonurl();
        this.title = commonOrder.getTitle();
        this.content = commonOrder.getContent();
        this.location = commonOrder.getLocation();
        this.time = commonOrder.getTime();
        this.timeformate = DataUtil.getLongToDateString(commonOrder.getTime());
        switch (commonOrder.getType()){
            case 0:
                this.type = "吃喝";
                break;
            case 1:
                this.type = "玩乐";
                break;
            case 2:
                this.type = "服饰";
                break;
            case 3:
                this.type = "其他";
                break;
        }
        this.leaveMessageCount = commonOrder.getMessageCount();

    }
}
