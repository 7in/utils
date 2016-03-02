package com.simon7in.strategy.controllers;

import com.simon7in.strategy.BaseScreen;
import com.simon7in.strategy.bo.WebReturnInfo;
import com.simon7in.strategy.util.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReplayErrorCaseController extends BaseScreen {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @RequestMapping(value = {"/replayErrorCaseByDetailId.do"}, method = RequestMethod.POST)
    public String exeDetail(ModelMap modelMap) {
        WebReturnInfo info = new WebReturnInfo();
        info.setSuccess(false);
        info.setCode("777");

        return JSONUtil.toJsonString(info);
    }
}
