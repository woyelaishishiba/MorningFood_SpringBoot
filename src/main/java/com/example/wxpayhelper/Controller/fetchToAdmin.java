package com.example.wxpayhelper.Controller;

import com.example.wxpayhelper.Entity.*;
import com.example.wxpayhelper.Entity.Insert.InsertFoodInfo;
import com.example.wxpayhelper.Service.UserService;
import com.example.wxpayhelper.Utils;
import com.example.wxpayhelper.WXHelper.WXPayUtil;
import com.example.wxpayhelper.WxMappingJackson2HttpMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class fetchToAdmin {

    static private String myKey = "0123456789";
    static private String appId = "wx737e15c801d5886a";
    static private String mySecret = "9a8099bd59cb48b27d088407208a8383";

    @Autowired
    UserService userService;

    @GetMapping(value = "/getUserList")
    @ResponseBody
    public List<User> justforTest() {
        return userService.getUserList();
    }

    @GetMapping(value = "/getFoodInfoList")
    @ResponseBody
    public List<FoodInfoForAdmin> getFoodInfoList() {
        List<FoodInfoForAdmin> list = userService.getFoodInfoList();
        return list;
    }

    @PostMapping(value = "/insertFoodInfo")
    @ResponseBody
    public String postFoodInfo(@RequestBody InsertFoodInfo foodInfo, @RequestParam String myHash) {

        //首先检验hash值是否相符
        if (!myHash.toUpperCase().equals(Utils.getMD5String(foodInfo.getList().get(0).getUserName() + myKey))) {
            return "wrong hash!";
        }
        if(!checkSessionKey(foodInfo.getOpenId(),foodInfo.getSessionKey())){
            return "wrong session_key";
        }
        for (int i = 0; i < foodInfo.getList().size(); i++) {
            for (int j = 0; j < foodInfo.getList().get(i).getNumb(); j++) {
                userService.insertFoodInfo(foodInfo.getList().get(i));
            }
        }
        return "success";
    }

    @GetMapping(value = "/deleteFoodInfoList")
    @ResponseBody
    public String deleteFoodInfoList(@RequestParam Integer id) {
        userService.deleteFoodInfoList(id);
        return "success";
    }

    @GetMapping(value = "/changeTriggerReady")
    @ResponseBody
    public String changeTriggerReady(@RequestParam Integer id, @RequestParam boolean isReady) {
        Map<String, Object> input = new HashMap<>();
        input.put("id", id);
        input.put("isReady", isReady);
        userService.changeReady(input);
        return "success";
    }

    @GetMapping(value = "/changeTriggerFinished")
    @ResponseBody
    public String changeTriggerFinished(@RequestParam Integer id, @RequestParam boolean isFinished) {
        Map<String, Object> input = new HashMap<>();
        input.put("id", id);
        input.put("isFinished", isFinished);
        userService.changeFinished(input);
        return "success";
    }

    @PostMapping(value = "/getFoodInfoListByUser")
    @ResponseBody
    public List<FoodInfoForAdmin> getFoodInfoListByUser(@RequestBody UserGetTrade userGetTrade) {
        Map<String, Object> input = new HashMap<>();
        input.put("startIndex", userGetTrade.getStartIndex());
        input.put("nickName", userGetTrade.getNickName());
        List<FoodInfoForAdmin> list = userService.getFoodInfoListByUser(input);
        return list;
    }

    @GetMapping(value = "/getFoodInfoListLength")
    @ResponseBody
    public int getFoodInfoListLength(@RequestParam String nickName) {
        return userService.getFoodInfoListLength(nickName);
    }


    private boolean checkSessionKey(String openId, String sessionKey) {
        RestTemplate restTemplate = new RestTemplate();// 发送request请求
        restTemplate.getMessageConverters().add(new WxMappingJackson2HttpMessageConverter());
        String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ appId +
                "&secret=" + mySecret;
        AccessToken accessToken = restTemplate.getForObject(tokenUrl, AccessToken.class);
        String signature = null;
        try {
            signature = WXPayUtil.HMACSHA256("", sessionKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String checkSessionUrl = "https://api.weixin.qq.com/wxa/checksession?access_token=" +
                accessToken.getAccess_token() + "&signature=" + signature  +
                "&openid=" + openId + "&sig_method=hmac_sha256";
        CheckSession response = restTemplate.getForObject(checkSessionUrl, CheckSession.class);
        return response.getErrcode() == 0 ? true : false;
    }

}
