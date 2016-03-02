package com.cainiao.strategy.controllers;

import com.cainiao.strategy.BaseScreen;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController extends BaseScreen{

    @RequestMapping(value = {"/home","/"},method = RequestMethod.GET)
    public String welcome(ModelMap modelMap) {
        modelMap.put("title","首页-规则广场");
        super.getMessage();

        return "/home";
    }

}
