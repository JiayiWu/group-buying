package com.fivedreamer.mapper;


/**
 * Created by Jiayiwu on 17/2/16.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */

public interface GroupMapper {


    public int renameGroup(int groupID, String name);

    public int removeGroup(int groupID);

    public int groupContact(int userID, int contactID, int groupID);

    public int moveContact(int userID, int contactID, int origin, int target);


}
