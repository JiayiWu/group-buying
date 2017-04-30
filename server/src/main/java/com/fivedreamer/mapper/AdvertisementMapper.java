package com.fivedreamer.mapper;

import com.fivedreamer.config.MessageInfo;
import com.fivedreamer.model.Advertisement;

import java.util.List;

/**
 * Created by Jiayiwu on 17/4/30.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public interface AdvertisementMapper {

    public int addAdvertisement(Advertisement advertisement);

    public int updateAdvertisement(int id,String imgURL,String contents,String targetURL);

    public int deleteAdvertisement(int id);

    public List<Advertisement> getAdvertisementList();
}
