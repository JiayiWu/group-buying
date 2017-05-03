package com.fivedreamer.vo;

import com.fivedreamer.model.CommonOrder;
import com.fivedreamer.utils.DataUtil;

/**
 * Created by Jiayiwu on 17/4/26.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public class ClassOrderListVO extends GroupBuyOrderRecommendListVO {

    public ClassOrderListVO() {
    }

    public ClassOrderListVO(CommonOrder commonOrder){
        this.userid = commonOrder.getUserid();
        this.id = commonOrder.getId();
        this.ordertype = 1;
        this.username = commonOrder.getUsername();
        this.usericonurl = commonOrder.getUsericonurl();
        this.title = commonOrder.getTitle();
        this.content = commonOrder.getContent();
        this.location = commonOrder.getLocation();
        this.time = commonOrder.getTime();
        this.timeformate = DataUtil.getLongToDateString(commonOrder.getTime());
        switch (commonOrder.getType()){
            case 0:
                this.type = "雅思托福";
                break;
            case 1:
                this.type = "BEC托业";
                break;
            case 2:
                this.type = "考研";
                break;
            case 3:
                this.type = "考证";
                break;
            case 4:
                this.type = "小语种";
                break;
            case 5:
                this.type = "考驾照";
                break;
            case 6:
                this.type = "来健身";
                break;
            case 7:
                this.type = "其他";
                break;
        }
        this.leaveMessageCount = commonOrder.getMessageCount();
    }

}
