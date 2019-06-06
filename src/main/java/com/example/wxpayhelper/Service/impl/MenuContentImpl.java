package com.example.wxpayhelper.Service.impl;

import com.example.wxpayhelper.Entity.MenuContent.ChildMenu;
import com.example.wxpayhelper.Entity.MenuContent.RootMenu;
import com.example.wxpayhelper.Mapper.MenuContentMapper;
import com.example.wxpayhelper.Service.MenuContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MenuContentImpl implements MenuContentService {

    @Autowired
    private MenuContentMapper menuContentMapper;

    @Override
    public List<RootMenu> getFirstMenuInfo() {
        return menuContentMapper.getFirstMenuInfo();
    }

    @Override
    public List<ChildMenu> getChildMenuByRootId(int id) {
        return menuContentMapper.getChildMenuByRootId(id);
    }

    @Override
    public void updateRootMenu(Map<String, Object> input) {
        menuContentMapper.updateRootMenuInfo(input);
    }

    @Override
    public ChildMenu getChildMenuById(Integer id) {
        return menuContentMapper.getChildMenuById(id);
    }

    @Override
    public void updateChildMenu(Map<String, Object> input) {
        menuContentMapper.updateChildMenu(input);
    }

    @Override
    public void deleteChildMenu(int id) {
        menuContentMapper.deleteChildMenu(id);
    }

    @Override
    public void insertRootMenu(Map<String, Object> input) {
        menuContentMapper.insertRootMenu(input);
    }

    @Override
    public void deleteRootMenu(int id) {
        menuContentMapper.deleteRootMenu(id);
    }

    @Override
    public void insertChildMenu(Map<String, Object> input) {
        menuContentMapper.insertChildMenu(input);
    }
}
