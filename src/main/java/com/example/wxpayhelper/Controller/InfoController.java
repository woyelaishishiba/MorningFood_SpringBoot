package com.example.wxpayhelper.Controller;

import com.example.wxpayhelper.Entity.FoodListInfo;
import com.example.wxpayhelper.Entity.PaymentInfo;
import com.example.wxpayhelper.Entity.PrepareInfo;
import com.example.wxpayhelper.Entity.UserInfo;
import com.example.wxpayhelper.Utils;
import com.example.wxpayhelper.WXHelper.WXPayUtil;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;
import java.util.Random;

@Controller
@EnableAutoConfiguration
public class InfoController {

    static private String appId = "wx5c41d2ed811215b8";
    static private String mySecret = "MYSECRET";
    static private String mch_id = "1230000109";
    //异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数
    static private String notify_url = "http://www.weixin.qq.com/wxpay/pay.php";
    static private String myOpenId = "";
    static private String key = "192006250b4c09247ec02edce69f6a2d";//商家密钥

    @PostMapping(value = "/getopenid")
    @ResponseBody
    String getOpenId(@RequestParam String code) {
        System.out.println(code);
        //端口是8080
        String fakeUrl = "https://www.easy-mock.com/mock/5cdccd6645a4a610b39976ab/example/getopenid";
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId;
        url += "&secret=" + mySecret;
        url += "&js_code=" + code + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();// 发送request请求
        UserInfo response = restTemplate.getForObject(fakeUrl, UserInfo.class);
        myOpenId = response.getOpenid();

        //假设返回的openid是myopenid
        return "myopenid";
    }

    @PostMapping(value = "/getpaymentinfo")
    @ResponseBody
    PaymentInfo preparePay(@RequestBody FoodListInfo foodListInfo) {
        //接受到用户订单信息开始与微信后台交互
        String firstSignurl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        String fakeGetPrepareIDUrl = "https://www.easy-mock.com/mock/5cdccd6645a4a610b39976ab/example/getPrepareID";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.APPLICATION_PROBLEM_JSON_UTF8;
        headers.setContentType(type);
//        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        String body = getFirstSignBody(foodListInfo.getTotal_fee());
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("data",body);
        HttpEntity<MultiValueMap<String, String>> r = new HttpEntity<>(requestEntity, headers);
        //返回的是xml格式的数据，perpared_id的值在其中
        PrepareInfo response = restTemplate.postForObject(fakeGetPrepareIDUrl, r, PrepareInfo.class);
        Map<String, String> result = null;
        try {
            result = WXPayUtil.xmlToMap(response.getData());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("解析失败");
        }

        String timeStamp = new Date().getTime() / 1000 + "";
        PaymentInfo paymentInfo = new PaymentInfo();
        String nonce_str = getNonceStr();
        String input_second = "appId=" + appId + "&nonceStr="+
                nonce_str + "&package=prepay_id=" + result.get("prepay_id") +
                "&signType=MD5&timeStamp=" + timeStamp;
        String paySign = getSign(input_second);
        paymentInfo.setNonceStr(nonce_str);
        paymentInfo.setTimeStamp(timeStamp);
        paymentInfo.setMypackage("prepay_id=" + result.get("prepay_id"));
        paymentInfo.setPaySign(paySign);
        paymentInfo.setSignType("MD5");
        return paymentInfo;
    }

    private String getFirstSignBody(int total_fee) {
        String body = "<xml>";
        body += "<appid>"+ appId + "</appid>";
        body += "<attach>恒隆总店</attach>";
        body += "<body>" + "腾讯充值中心-QQ会员充值" + "</body>";
        body += "<mch_id>" + mch_id + "</mch_id>"; //商户号
        String nounceStr = getNonceStr();
        body += "<nonce_str>" + nounceStr + "</nonce_str>";
        body += "<notify_url>" + notify_url + "</notify_url>";
        body += "<openid>" + myOpenId + "</openid>";
        /* 商户订单号商户自己生成，唯一
            重新发起一笔支付要使用原订单号，避免重复支付
         */
        String out_trade_no = getNonceStr();
        body += "<out_trade_no>" + out_trade_no + "</out_trade_no>";
        String spbill_create_ip = "14.23.150.211";//调用微信支付API的机器IP
        body += "<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>";
        body += "<total_fee>" + total_fee + "</total_fee>";
        body += "<trade_type>JSAPI</trade_type>";
        String allInput = Utils.combineParam(
                "appid", appId,
                "attach", "恒隆总店",
                "body", "腾讯充值中心-QQ会员充值",
                "mch_id", mch_id,
                "nonce_str", nounceStr,
                "notify_url", notify_url,
                "openid", myOpenId,
                "out_trade_no", out_trade_no,
                "spbill_create_ip", spbill_create_ip,
                "total_fee", total_fee+"",
                "trade_type", "JSAPI"
                );
        body += "<sign>" + getSign(allInput) + "</sign>";
        body += "</xml>";
        return body;

    }

    private String getNonceStr(){
        String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuffer ans = new StringBuffer();
        Random random = new Random();
        for(int i = 0; i < 32;i++){
            ans.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
        }
        return ans.toString();
    }

    private String getSign(String input){
        String ans = "";
        String stringSignTemp = input + "&key=" + key;
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(stringSignTemp.getBytes());
            byte[] result = md.digest();
            ans = Utils.byteToHexString(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return ans.toUpperCase();
    }

    @GetMapping(value = "/")
    @ResponseBody
    String justforTest() {
        System.out.println(getFirstSignBody(100));
        return getFirstSignBody(100);
    }
}
