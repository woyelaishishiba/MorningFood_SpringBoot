package com.example.wxpayhelper.Mapper;

import com.example.wxpayhelper.Entity.FoodInfoForAdmin;
import com.example.wxpayhelper.Entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

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
