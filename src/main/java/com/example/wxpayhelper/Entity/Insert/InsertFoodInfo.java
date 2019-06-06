package com.example.wxpayhelper.Entity.Insert;

import com.example.wxpayhelper.Entity.FoodInfoForAdmin;

import java.util.List;

public class InsertFoodInfo {

    private String sessionKey;
    private String openId;
    private List<FoodInfoForAdmin> list;

    public List<FoodInfoForAdmin> getList() {
        return list;
    }

    public void setList(List<FoodInfoForAdmin> list) {
        this.list = list;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

}
