package com.cainiao.strategy;

import com.cainiao.solo.dao.RuleUnitDAO;
import com.cainiao.solo.entity.RuleUnitDO;
import com.google.gson.Gson;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;

/**
 * Created by SIMON on 16/1/1.
 */
//@ContextConfiguration(locations = {"classpath:dao-test.xml"})
public class TestDAO {

//    @Resource
//    private RuleUnitDAO ruleUnitDAO;

    @Test
    public void unitTest() {
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("dao-test.xml");
//        RuleUnitDAO ruleUnitDAO= (RuleUnitDAO)ctx.getBean("ruleUnitDAO");
//        RuleUnitDO ruleUnitDO = new RuleUnitDO();
//        ruleUnitDO.setCode("mybatistest");
//        ruleUnitDAO.insert(ruleUnitDO);
//        System.out.print(new Gson().toJson(ruleUnitDO));
    }
}
