package com.fivedreamer.vo;

import com.fivedreamer.model.CommonOrder;
import com.fivedreamer.utils.DataUtil;

/**
 * Created by Jiayiwu on 17/4/26.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public class MailOrderListVO extends GroupBuyOrderRecommendListVO {

    public MailOrderListVO() {
    }

    public MailOrderListVO(CommonOrder commonOrder){
        this.userid = commonOrder.getUserid();
        this.id = commonOrder.getId();
        this.ordertype = 2;
        this.username = commonOrder.getUsername();
        this.usericonurl = commonOrder.getUsericonurl();
        this.title = commonOrder.getTitle();
        this.content = commonOrder.getContent();
        this.location = commonOrder.getLocation();
        this.time = commonOrder.getTime();
        this.timeformate = DataUtil.getLongToDateString(commonOrder.getTime());
        switch (commonOrder.getType()){
            case 0:
                this.type = "日本";
                break;
            case 1:
                this.type = "韩国";
                break;
            case 2:
                this.type = "美国";
                break;
            case 3:
                this.type = "欧洲";
                break;
            case 4:
                this.type = "澳洲";
                break;
            case 5:
                this.type = "港澳台泰";
                break;
            case 6:
                this.type = "其他地区";
                break;
            case 7:
                this.type = "网店拼邮";
                break;
        }
        this.leaveMessageCount = commonOrder.getMessageCount();
    }
}
