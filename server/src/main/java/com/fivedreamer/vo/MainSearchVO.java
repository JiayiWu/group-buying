package com.fivedreamer.vo;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jiayiwu on 17/4/26.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public class MainSearchVO {

    List<BuyOrderListVO> buyOrderListVOList = new LinkedList<BuyOrderListVO>();

    List<CarOrderListVO> carOrderListVOList = new LinkedList<CarOrderListVO>();

    List<ClassOrderListVO> classOrderListVOList = new LinkedList<ClassOrderListVO>();

    List<MailOrderListVO> mailOrderListVOList = new LinkedList<MailOrderListVO>();


    public void addrecommendBuyVO(BuyOrderListVO buyOrderListVO){
        buyOrderListVOList.add(buyOrderListVO);
    }

    public void addrecommendCarVO(CarOrderListVO carOrderListVO){
        carOrderListVOList.add(carOrderListVO);
    }

    public void addrecommendClassVO(ClassOrderListVO classOrderListVO){
        classOrderListVOList.add(classOrderListVO);
    }

    public void addrecommendMailVO(MailOrderListVO mailOrderListVO){
        mailOrderListVOList.add(mailOrderListVO);
    }

    public List<BuyOrderListVO> getBuyOrderListVOList() {
        return buyOrderListVOList;
    }

    public void setBuyOrderListVOList(List<BuyOrderListVO> buyOrderListVOList) {
        this.buyOrderListVOList = buyOrderListVOList;
    }

    public List<CarOrderListVO> getCarOrderListVOList() {
        return carOrderListVOList;
    }

    public void setCarOrderListVOList(List<CarOrderListVO> carOrderListVOList) {
        this.carOrderListVOList = carOrderListVOList;
    }

    public List<ClassOrderListVO> getClassOrderListVOList() {
        return classOrderListVOList;
    }

    public void setClassOrderListVOList(List<ClassOrderListVO> classOrderListVOList) {
        this.classOrderListVOList = classOrderListVOList;
    }

    public List<MailOrderListVO> getMailOrderListVOList() {
        return mailOrderListVOList;
    }

    public void setMailOrderListVOList(List<MailOrderListVO> mailOrderListVOList) {
        this.mailOrderListVOList = mailOrderListVOList;
    }
}
