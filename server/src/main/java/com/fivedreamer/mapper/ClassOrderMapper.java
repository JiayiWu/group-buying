package com.fivedreamer.mapper;

import com.fivedreamer.model.CommonOrder;

import java.util.List;

/**
 * Created by Jiayiwu on 17/4/29.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public interface ClassOrderMapper {

    /**
     * 用于正在进行拼单的查询
     */
    public List<CommonOrder> getClassOrderNowByUserID(int id);

    /**
     * 用于正在已经拼单成功的查询
     */
    public List<CommonOrder> getClassOrderSuccessByUserID(int id);

    /**
     * 通过评论查找参与过的订单
     */
    public List<CommonOrder> getClassOrderByComments(int id);


    public List<CommonOrder> getClassOrderListByType(int type);

    public CommonOrder getClassOrderDetail(int id);

    public int addClassOrder(CommonOrder commonOrder);

    public List<CommonOrder> getClassRecommendList();

    public int modifyClassOrder(int id,String title,String content,String location,int type,long time);

    public int successOrder(int id);

    public int deleteOrder(int id);

    public int descMessageCount(int id);

    public int ascMessageCount(int id);
}
