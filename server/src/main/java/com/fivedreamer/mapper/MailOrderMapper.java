package com.fivedreamer.mapper;

import com.fivedreamer.model.CommonOrder;
import com.fivedreamer.vo.MailOrderDetailVO;
import com.fivedreamer.vo.MailOrderListVO;

import java.util.List;

/**
 * Created by Jiayiwu on 17/4/29.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public interface MailOrderMapper {

    /**
     * 用于正在进行拼单的查询
     */
    public List<CommonOrder> getMailOrderNowByUserID(int id);

    /**
     * 用于正在已经拼单成功的查询
     */
    public List<CommonOrder> getMailOrderSuccessByUserID(int id);

    /**
     * 通过评论查找参与过的订单
     */
    public List<CommonOrder> getMailOrderByComments(int id);


    public List<CommonOrder> getMailOrderListByType(int type);

    public CommonOrder getMailOrderDetail(int id);

    public int addMailOrder(CommonOrder commonOrder);

    public List<CommonOrder> getMailRecommendList();

    public int modifyMailOrder(int id,String title,String content,String location,int type,long time);

    public int successOrder(int id);

    public int deleteOrder(int id);

    public int descMessageCount(int id);

    public int ascMessageCount(int id);

    public List<CommonOrder> getSearch(String keyWord);


}
