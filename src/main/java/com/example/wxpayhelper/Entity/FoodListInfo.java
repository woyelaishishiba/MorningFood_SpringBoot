package com.example.wxpayhelper.Entity;

import java.util.List;

public class FoodListInfo {

    private List<String> foodNum;
    private int total_fee;
    private String openid;

    public List<String> getFoodNum() {
        return foodNum;
    }

    public void setFoodNum(List<String> foodNum) {
        this.foodNum = foodNum;
    }

    public int getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

}
