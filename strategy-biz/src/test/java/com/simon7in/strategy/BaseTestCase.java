package com.simon7in.strategy;

import com.taobao.hsf.hsfunit.HSFEasyStarter;
import com.taobao.itest.ITestSpringContextBaseCase;

/**
 * Created by jiayu.shenjy on 2016/2/1.
 */
public class BaseTestCase extends ITestSpringContextBaseCase {

    static {
        try {
            HSFEasyStarter.start("hsf", "1.8.1.3");
        } catch (Exception e) {
            System.err.println("HSF容器启动失败，请检查HSF.SAR路径是否合法!");
            throw new RuntimeException(e);
        }
    }
}
