package com.fivedreamer.service;

import com.fivedreamer.config.MessageInfo;
import com.fivedreamer.mapper.*;

import com.fivedreamer.model.CarOrder;
import com.fivedreamer.model.CommonOrder;
import com.fivedreamer.model.Tag;
import com.fivedreamer.model.User;
import com.fivedreamer.utils.SHA256Util;
import com.fivedreamer.vo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jiayiwu on 17/4/29.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
@Service
@EnableTransactionManagement
@Transactional(rollbackFor = Exception.class)
public class UserService {


    @Resource
    BuyOrderMapper buyOrderMapper;

    @Resource
    CarOrderMapper carOrderMapper;

    @Resource
    ClassOrderMapper classOrderMapper;

    @Resource
    MailOrderMapper mailOrderMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    TagMapper tagMapper;

    public MessageInfo getUserInfo(int id){
        try {
            UserInfoVO userInfoVO = userMapper.getUserInfo(id);
            List<Tag> tags = tagMapper.getTags(id);
            if (userInfoVO != null) {
                userInfoVO.setTags(tags);
                List<GroupBuyOrderRecommendListVO> list = ((List<GroupBuyOrderRecommendListVO>)getSelfPostOrder(id).getObject());
                userInfoVO.setPostCount(list!=null?list.size():0);
                userInfoVO.setList(list);
                List<GroupBuyOrderRecommendListVO> list1 = (List<GroupBuyOrderRecommendListVO>)getSelfGroupOrder(id).getObject();
                userInfoVO.setAttendCount(list1!=null?list1.size():0);
                List<GroupBuyOrderRecommendListVO> list2 = (List<GroupBuyOrderRecommendListVO>)getSelfCommentsOrder(id).getObject();
                userInfoVO.setCommentCount(list2!=null?list2.size():0);
                return new MessageInfo(true,userInfoVO, "检索成功");
            }
            return new MessageInfo(false,"ID不存在");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"数据库检索错误");
        }

    }

    public MessageInfo getFansList(int id){

       try{
           List<UserListVO> list = userMapper.getFansList(id);
           if (list != null)
               return new MessageInfo(true,list,"检索成功");

           return new MessageInfo(true,"该用户无粉丝");
       }catch (Exception e){
           e.printStackTrace();
           return new MessageInfo(false,"数据库检索错误");
       }
    }

    public MessageInfo getFocusList(int id){

        try{
            List<UserListVO> list = userMapper.getFocusList(id);
            if (list != null)
                return new MessageInfo(true,list,"检索成功");
            return new MessageInfo(true,"该用户还未关注任何用户");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"数据库检索错误");
        }
    }

    public MessageInfo getSelfPostOrder(int id){
        try{
            List<CarOrder> carOrder = carOrderMapper.getCarOrderNowByUserID(id);
            List<CommonOrder> buyOrder = buyOrderMapper.getBuyOrderNowByUserID(id);
            List<CommonOrder>  mailOrder = mailOrderMapper.getMailOrderByComments(id);
            List<CommonOrder>  classOrder = classOrderMapper.getClassOrderNowByUserID(id);
            List<GroupBuyOrderRecommendListVO> result = new LinkedList<GroupBuyOrderRecommendListVO>();
            for (CarOrder tem : carOrder)
                result.add(new CarOrderListVO(tem));

            for (CommonOrder tem : buyOrder)
                result.add(new BuyOrderListVO(tem));

            for (CommonOrder tem : mailOrder)
                result.add(new MailOrderListVO(tem));

            for (CommonOrder tem : classOrder)

                result.add(new ClassOrderListVO(tem));

            Collections.sort(result);
            return new MessageInfo(true,result,"数据获取成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"数据库检索错误");
        }
    }

    public MessageInfo getSelfGroupOrder(int id){
        try{
            List<CarOrder> carOrder = carOrderMapper.getCarOrderSuccessByUserID(id);
            List<CommonOrder> buyOrder = buyOrderMapper.getBuyOrderSuccessByUserID(id);
            List<CommonOrder>  mailOrder = mailOrderMapper.getMailOrderSuccessByUserID(id);
            List<CommonOrder>  classOrder = classOrderMapper.getClassOrderSuccessByUserID(id);
            List<GroupBuyOrderRecommendListVO> result = new LinkedList<GroupBuyOrderRecommendListVO>();
            for (CarOrder tem : carOrder)
                result.add(new CarOrderListVO(tem));

            for (CommonOrder tem : buyOrder)
                result.add(new BuyOrderListVO(tem));

            for (CommonOrder tem : mailOrder)
                result.add(new MailOrderListVO(tem));

            for (CommonOrder tem : classOrder)
                result.add(new ClassOrderListVO(tem));

            Collections.sort(result);

            return new MessageInfo(true,result,"数据获取成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"数据库检索错误");
        }
    }



    public MessageInfo getSelfCommentsOrder(int id){
        try{
            List<CarOrder> carOrder = carOrderMapper.getCarOrderByComments(id);
            List<CommonOrder> buyOrder = buyOrderMapper.getBuyOrderByComments(id);
            List<CommonOrder>  mailOrder = mailOrderMapper.getMailOrderByComments(id);
            List<CommonOrder>  classOrder = classOrderMapper.getClassOrderByComments(id);
            List<GroupBuyOrderRecommendListVO> result = new LinkedList<GroupBuyOrderRecommendListVO>();
            for (CarOrder tem : carOrder)
                result.add(new CarOrderListVO(tem));

            for (CommonOrder tem : buyOrder)
                result.add(new BuyOrderListVO(tem));

            for (CommonOrder tem : mailOrder)
                result.add(new MailOrderListVO(tem));

            for (CommonOrder tem : classOrder)
                result.add(new ClassOrderListVO(tem));

            Collections.sort(result);
            return new MessageInfo(true,result,"数据获取成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"数据库检索错误");
        }
    }
    public MessageInfo updateUserInfo(int id, String nickName, String school, String sex, String iconUrl, String backgroundUrl, String[] tag){
            try {
                if (userMapper.updateUserInfo(id, nickName, school, sex, iconUrl, backgroundUrl) > 0) {
                    for (String tem : tag) {
                        tagMapper.insertTag(id, tem);
                    }
                    return new MessageInfo(true,"数据更新成功");
                }
                    return new MessageInfo(false,"用户ID不存在");
            }catch (Exception e){
                e.printStackTrace();
                return new MessageInfo(false,"服务器错误,无法进行数据更新");
            }
    }

    public MessageInfo updateUserPassword(int id,String oldPassword,String newPassword){
        User user = userMapper.getUser(id);
        if (user != null){
            if (user.getPassword().equals(SHA256Util.Encrypt(oldPassword))){
                userMapper.updatePassword(id,SHA256Util.Encrypt(newPassword));
                return new MessageInfo(true,"密码修改成功");
            }else {
                return new MessageInfo(false,"原密码错误");
            }
        }
        return new MessageInfo(false,"用户不存在");
    }

    public MessageInfo updateAuthentication(int id,String url){
        try {

            userMapper.updateAuthentication(id,url);
            return new MessageInfo(true,"认证成功");

        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"服务器错误,修改失败");
        }
    }

    public MessageInfo isAuthentication(int id){
        try{
            User user = userMapper.getUser(id);
            return new MessageInfo(true,user.isAuthentication(),"获取认证信息成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"服务器错误,修改失败");
        }
    }

    public MessageInfo getSIC_URL(int id){
        try{
            User user = userMapper.getUser(id);
            return new MessageInfo(true,user.getSic_url(),"获取学生证图片信息成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MessageInfo(false,"服务器错误,修改失败");
        }
    }



}
