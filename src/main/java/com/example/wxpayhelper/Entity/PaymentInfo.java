package com.example.wxpayhelper.Entity;

public class PaymentInfo {

    private String timeStamp;
    private String nonceStr;
    private String mypackage;
    private String signType;
    private String paySign;

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getMypackage() {
        return mypackage;
    }

    public void setMypackage(String mypackage) {
        this.mypackage = mypackage;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }

}
