package com.example.wxpayhelper.Service;

import com.example.wxpayhelper.Entity.FoodInfoForAdmin;
import com.example.wxpayhelper.Entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> getUserList();

    List<FoodInfoForAdmin> getFoodInfoList();

    void insertUserTest(User user);

    void insertFoodInfo(FoodInfoForAdmin foodInfo);

    void changeReady(Map<String, Object> input);

    void changeFinished(Map<String, Object> input);

    void deleteFoodInfoList(Integer id);

    List<FoodInfoForAdmin> getFoodInfoListByUser(Map<String,Object> input);

    int getFoodInfoListLength(String nickName);
}
