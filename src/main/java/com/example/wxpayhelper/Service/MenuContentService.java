package com.example.wxpayhelper.Service;

import com.example.wxpayhelper.Entity.MenuContent.ChildMenu;
import com.example.wxpayhelper.Entity.MenuContent.RootMenu;

import java.util.List;
import java.util.Map;

public interface MenuContentService {

    List<RootMenu> getFirstMenuInfo();

    List<ChildMenu> getChildMenuByRootId(int id);

    void updateRootMenu(Map<String,Object> input);

    ChildMenu getChildMenuById(Integer id);

    void updateChildMenu(Map<String,Object> input);

    void deleteChildMenu(int id);

    void insertRootMenu(Map<String,Object> input);

    void deleteRootMenu(int id);

    void insertChildMenu(Map<String,Object> input);
}
