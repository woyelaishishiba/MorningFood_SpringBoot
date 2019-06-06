package com.example.wxpayhelper.Mapper;

import com.example.wxpayhelper.Entity.MenuContent.ChildMenu;
import com.example.wxpayhelper.Entity.MenuContent.RootMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MenuContentMapper {

    List<RootMenu> getFirstMenuInfo();

    List<ChildMenu> getChildMenuByRootId(int id);

    void updateRootMenuInfo(Map<String,Object> input);

    ChildMenu getChildMenuById(Integer id);

    void updateChildMenu(Map<String,Object> input);

    void deleteChildMenu(int id);

    void insertRootMenu(Map<String,Object> input);

    void deleteRootMenu(int id);

    void insertChildMenu(Map<String,Object> input);
}
