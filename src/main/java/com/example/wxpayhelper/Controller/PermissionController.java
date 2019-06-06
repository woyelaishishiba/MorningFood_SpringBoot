package com.example.wxpayhelper.Controller;

import com.example.wxpayhelper.Entity.UserCode;
import com.example.wxpayhelper.Entity.UserInfo;
import com.example.wxpayhelper.Service.PermissionService;
import com.example.wxpayhelper.WxMappingJackson2HttpMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.converter.cbor.MappingJackson2CborHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@EnableAutoConfiguration
public class PermissionController {

    static private String appId = "wx737e15c801d5886a";
    static private String mySecret = "9a8099bd59cb48b27d088407208a8383";

    @Autowired
    PermissionService permissionService;

    @GetMapping(value = "/hasPermission")
    @ResponseBody
    public boolean hasPermission(@RequestParam String nickName){
        return permissionService.hasPermission(nickName) != 0 ? true : false;
    }

    @PostMapping(value = "/getopenidandsessionkey")
    @ResponseBody
    public UserInfo getOpenIdSessionKey(@RequestBody UserCode userCode) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId;
        url += "&secret=" + mySecret;
        url += "&js_code=" + userCode.getCode() + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();// 发送request请求
        restTemplate.getMessageConverters().add(new WxMappingJackson2HttpMessageConverter());
        UserInfo response = restTemplate.getForObject(url, UserInfo.class);
        //session_key不应该传到服务器外的环境
        return response;
    }
}
