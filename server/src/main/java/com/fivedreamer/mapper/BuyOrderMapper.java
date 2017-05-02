package com.fivedreamer.mapper;

import com.fivedreamer.model.CommonOrder;

import java.util.List;


/**
 * Created by Jiayiwu on 17/4/29.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */

public interface BuyOrderMapper {

    /**
     * 用于正在进行拼单的查询
     */
    public List<CommonOrder> getBuyOrderNowByUserID(int id);

    /**
     * 用于正在已经拼单成功的查询
     */
    public List<CommonOrder> getBuyOrderSuccessByUserID(int id);

    /**
     * 通过评论查找参与过的订单
     */
    public List<CommonOrder> getBuyOrderByComments(int id);


    public List<CommonOrder> getBuyOrderListByType(int type);

    public CommonOrder getBuyOrderDetail(int id);

    public int addBuyOrder(CommonOrder commonOrder);

    public List<CommonOrder> getBuyRecommendList();

    public int modifyBuyOrder(int id,String title,String content,String location,int type,long time);

    public int successOrder(int id);

    public int deleteOrder(int id);

    public int descMessageCount(int id);

    public int ascMessageCount(int id);

    public List<CommonOrder> getSearch(String keyWord);
}
