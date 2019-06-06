package com.example.wxpayhelper.Service.impl;

import com.example.wxpayhelper.Mapper.PermissionMapper;
import com.example.wxpayhelper.Mapper.UserMapper;
import com.example.wxpayhelper.Service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public int hasPermission(String nickName) {
        return permissionMapper.hasPermission(nickName);
    }

}
