package com.example.wxpayhelper.Entity.Insert;

public class InsertChildMenu {

    private int id;
    private int motherId;
    private String foodName;
    private String picUrl;
    private double price;
    private float rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMotherId() {
        return motherId;
    }

    public void setMotherId(int motherId) {
        this.motherId = motherId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

}
