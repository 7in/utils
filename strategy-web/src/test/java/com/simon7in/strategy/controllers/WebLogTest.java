package com.simon7in.strategy.controllers;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by SIMON on 16/1/2.
 */
public class WebLogTest {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void logTest(){
        try {
            throw new Exception("你太优秀了！");
        }catch (Exception e){
            log.error("com.simon7in.strategy.LogTest.logTest!!!!",e);

        }
    }
}
