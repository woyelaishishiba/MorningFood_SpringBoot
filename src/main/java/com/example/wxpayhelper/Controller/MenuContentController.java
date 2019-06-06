package com.example.wxpayhelper.Controller;

import com.example.wxpayhelper.Entity.Insert.InsertChildMenu;
import com.example.wxpayhelper.Entity.Insert.InsertRootMenu;
import com.example.wxpayhelper.Entity.MenuContent.ChildMenu;
import com.example.wxpayhelper.Entity.MenuContent.FinalMenuContent;
import com.example.wxpayhelper.Entity.MenuContent.RootMenu;
import com.example.wxpayhelper.Service.MenuContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class MenuContentController {

    @Autowired
    private MenuContentService menuContentService;

    @GetMapping("/restaurant/menu")
    @ResponseBody
    public List<FinalMenuContent> getAllMenuContent(){

        List<RootMenu> rootMenuList = menuContentService.getFirstMenuInfo();
        List<FinalMenuContent> finalMenuContents = new ArrayList<>();

        for (int i=0;i<rootMenuList.size();i++){
            List<ChildMenu> childMenuList = menuContentService.getChildMenuByRootId(rootMenuList.get(i).getId());
            FinalMenuContent finalMenuContent = new FinalMenuContent();
            finalMenuContent.setId(rootMenuList.get(i).getId());
            finalMenuContent.setTypeName(rootMenuList.get(i).getTypeName());
            finalMenuContent.setMenuContent(childMenuList);
            finalMenuContents.add(finalMenuContent);
        }
        return finalMenuContents;
    }

    @PostMapping("/restaurant/updaterootmenu")
    @ResponseBody
    public String updateRootMenu(@RequestBody RootMenu updateRootMenu){
        Map<String, Object> input = new HashMap<>();
        input.put("id",updateRootMenu.getId());
        input.put("typeName",updateRootMenu.getTypeName());
        menuContentService.updateRootMenu(input);
        return "success";
    }

    @PostMapping("/restaurant/insertrootmenu")
    @ResponseBody
    public String insertRootMenu(@RequestBody InsertRootMenu insertRootMenu){
        Map<String, Object> input = new HashMap<>();
        input.put("typeName",insertRootMenu.getTypeName());
        menuContentService.insertRootMenu(input);
        return "success";
    }

    @GetMapping("/restaurant/deleterootmenu")
    @ResponseBody
    public String deleteRootMenu(@RequestParam int id){
        System.out.println(id);
        menuContentService.deleteRootMenu(id);
        return "success";
    }

    @GetMapping("/restaurant/menu/childmenu")
    @ResponseBody
    public ChildMenu getChildMenu(@RequestParam Integer id){
        return menuContentService.getChildMenuById(id);
    }

    @PostMapping("/restaurant/menu/updatechildmenu")
    @ResponseBody
    public String updateChildMenu(@RequestBody ChildMenu updateChildMenu){
        Map<String, Object> input = new HashMap<>();
        input.put("id",updateChildMenu.getId());
        input.put("foodName",updateChildMenu.getFoodName());
        input.put("picUrl",updateChildMenu.getPicUrl());
        input.put("price",updateChildMenu.getPrice());
        input.put("rating",updateChildMenu.getRating());
        input.put("isShown",true);
        menuContentService.updateChildMenu(input);
        return "success";
    }

    @GetMapping("/restaurant/menu/deletechildmenu")
    @ResponseBody
    public String deleteChildMenu(@RequestParam Integer id){
        menuContentService.deleteChildMenu(id);
        return "success";
    }

    @PostMapping("/restaurant/menu/insertchildmenu")
    @ResponseBody
    public String insertChildMenu(@RequestBody InsertChildMenu insertChildMenu){
        Map<String, Object> input = new HashMap<>();
        input.put("foodName",insertChildMenu.getFoodName());
        input.put("picUrl",insertChildMenu.getPicUrl());
        input.put("motherId",insertChildMenu.getMotherId());
        input.put("price",insertChildMenu.getPrice());
        input.put("rating",insertChildMenu.getRating());
        input.put("isShown",true);
        System.out.println(insertChildMenu.getFoodName());
        menuContentService.insertChildMenu(input);
        return "success";
    }

}
