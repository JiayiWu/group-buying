package com.fivedreamer.mapper;

import com.fivedreamer.model.Admin;

/**
 * Created by Jiayiwu on 17/5/3.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public interface AdminMapper {


    public Admin getAdmin();

    public int modifyPassword(String password);
}
