package com.fivedreamer.service;

import com.fivedreamer.config.MessageInfo;
import com.fivedreamer.mapper.AdvertisementMapper;
import com.fivedreamer.model.Advertisement;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Jiayiwu on 17/4/30.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
@Service
public class AdvertisementService {

    @Resource
    AdvertisementMapper advertisementMapper;


    public MessageInfo addAdvertisement(String imgURL,String contents,String targetURL){
        try {
            Advertisement advertisement = new Advertisement(imgURL,contents,targetURL);
            advertisementMapper.addAdvertisement(advertisement);
            return new MessageInfo(true,advertisement,"广告添加成功");
        }catch (Exception e){
            e.printStackTrace();

        }
        return new MessageInfo(false,"服务器错误,广告添加失败");
    }

    public MessageInfo updateAdvertisement(int id,String imgURL,String contents,String targetURL){

        try {
            advertisementMapper.updateAdvertisement(id,imgURL,contents,targetURL);
            return new MessageInfo(true,"广告修改成功");
        }catch (Exception e){
            e.printStackTrace();

        }
        return new MessageInfo(false,"服务器错误,广告修改失败");
    }

    public MessageInfo deleteAdvertisement(String[] id){
        try {
            for (String tem:id) {
                advertisementMapper.deleteAdvertisement(Integer.parseInt(tem));
            }
            return new MessageInfo(true,"广告删除成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new MessageInfo(false,"服务器错误,广告删除失败");

    }

    public MessageInfo getAdvertisementList(){

        try {
            List<Advertisement> list = advertisementMapper.getAdvertisementList();
            return new MessageInfo(true,list,"广告列表获取成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new MessageInfo(false,"服务器错误,广告列表获取失败");


    }

    public MessageInfo getAdvertisementBackList(){

        try {
            List<Advertisement> list = advertisementMapper.getAdvertisementList();
            for (int i =0;i<list.size();i++){
                Advertisement tem = list.get(i);
                tem.setImgURL(tem.getImgURL().length()>30?tem.getImgURL().substring(0,30)+"......":tem.getImgURL());
            }
            return new MessageInfo(true,list,"广告列表获取成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new MessageInfo(false,"服务器错误,广告列表获取失败");


    }
}
