package com.example.wxpayhelper.Mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PermissionMapper {
    int hasPermission(String nickName);
}
