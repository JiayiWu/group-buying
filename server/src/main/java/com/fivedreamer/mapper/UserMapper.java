package com.fivedreamer.mapper;

import com.fivedreamer.model.User;
import com.fivedreamer.vo.UserInfoVO;
import com.fivedreamer.vo.UserListVO;

import java.util.List;

/**
 * Created by Jiayiwu on 17/4/29.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public interface UserMapper {


    public UserInfoVO getUserInfo(int id);

    public User getUser(int id);

    public User getUserByPhoneNumber(String PhoneNumber);

    public int register(User user);

    public List<UserListVO> getFansList(int id);

    public List<UserListVO> getFocusList(int id);

    public int updateUserInfo(int id,String nickName,String school,String sex,String iconUrl,String backgroundUrl);

    public int updatePassword(int id,String password);

    public int updateAuthentication(int id,String url);





}
