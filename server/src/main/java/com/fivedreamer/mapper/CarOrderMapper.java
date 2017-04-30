package com.fivedreamer.mapper;

import com.fivedreamer.model.CarOrder;

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
}
