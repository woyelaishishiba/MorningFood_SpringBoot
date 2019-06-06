package com.example.wxpayhelper.Entity.MenuContent;

import java.util.List;

public class FinalMenuContent {

    private int id;
    private String typeName;
    private List<ChildMenu> menuContent;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<ChildMenu> getMenuContent() {
        return menuContent;
    }

    public void setMenuContent(List<ChildMenu> menuContent) {
        this.menuContent = menuContent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
