package com.fivedreamer.mapper;

import com.fivedreamer.model.CarOrder;
import com.fivedreamer.model.CommonOrder;

import java.util.List;


/**
 * Created by Jiayiwu on 17/4/29.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public interface CarOrderMapper {

    /**
     * 用于正在进行拼单的查询
     */
    public List<CarOrder> getCarOrderNowByUserID(int id);

    /**
     * 用于正在已经拼单成功的查询
     */
    public List<CarOrder> getCarOrderSuccessByUserID(int id);


    /**
     * 通过评论查找参与过的订单
     */
    public List<CarOrder> getCarOrderByComments(int id);

    public List<CarOrder> getCarOrderListByType(String direction);

    public String[] getAllDirection();

    public CarOrder getCarOrderDetail(int id);

    public int addCarOrder(CarOrder carOrder);

    public List<CarOrder> getCarRecommendList();

    public int modifyCarOrder(int id,String title,String content,String location,String direction,long time);

    public int successOrder(int id);

    public int deleteOrder(int id);

    public int descMessageCount(int id);

    public int ascMessageCount(int id);

    public List<CarOrder> getSearch(String keyWord);
}
