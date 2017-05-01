package com.fivedreamer.controller;
import com.fivedreamer.config.MessageInfo;
import com.fivedreamer.utils.AuthHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * Created by Jiayiwu on 17/4/30.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    /**
     * 获取普通上传凭证
     */
    @RequestMapping(value = "/token")
    @ResponseBody
    public MessageInfo getUploadToken() {
        return new MessageInfo(true,  AuthHelper.getToken(),"获取成功");
    }

    /**
     * 获取覆盖上传凭证
     *
     * @param fileName 文件名
     */
    @RequestMapping(value = "/token/cover")
    @ResponseBody
    public MessageInfo getUploadToken(@RequestParam String fileName) {
        return new MessageInfo(true, AuthHelper.getToken(fileName),"获取成功");
    }
}

