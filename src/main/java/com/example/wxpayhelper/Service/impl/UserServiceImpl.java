package com.example.wxpayhelper.Service.impl;

import com.example.wxpayhelper.Entity.FoodInfoForAdmin;
import com.example.wxpayhelper.Entity.User;
import com.example.wxpayhelper.Mapper.UserMapper;
import com.example.wxpayhelper.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public List<FoodInfoForAdmin> getFoodInfoList() {
        return userMapper.getFoodInfoList();
    }

    @Override
    public void insertUserTest(User user) {
        userMapper.insertUserTest(user);
    }

    @Override
    public void insertFoodInfo(FoodInfoForAdmin foodInfo) {
        userMapper.insertFoodInfo(foodInfo);
    }

    @Override
    public void changeReady(Map<String, Object> input) {
        userMapper.changeReady(input);
    }

    @Override
    public void changeFinished(Map<String, Object> input) {
        userMapper.changeFinished(input);
    }

    @Override
    public void deleteFoodInfoList(Integer id) {
        userMapper.deleteFoodInfoList(id);
    }

    @Override
    public List<FoodInfoForAdmin> getFoodInfoListByUser(Map<String, Object> input) {
        return userMapper.getFoodInfoListByUser(input);
    }

    @Override
    public int getFoodInfoListLength(String nickName) {
        return userMapper.getFoodInfoListLength(nickName);
    }

}
