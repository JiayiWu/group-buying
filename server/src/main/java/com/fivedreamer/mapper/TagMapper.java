package com.fivedreamer.mapper;

import com.fivedreamer.model.Tag;

import java.util.List;

/**
 * Created by Jiayiwu on 17/4/29.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public interface TagMapper {

    public List<Tag> getTags(int id);

    public int insertTag(int userid,String content);
}
