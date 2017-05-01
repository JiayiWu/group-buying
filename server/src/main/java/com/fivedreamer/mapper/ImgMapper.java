package com.fivedreamer.mapper;

/**
 * Created by Jiayiwu on 17/4/29.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public interface ImgMapper {

    public String[] getImgUrl(int type ,int orderid);

    public int deleteImg(int type,int orderid);

    public int addImg(int type,int orderid,String url);

}
