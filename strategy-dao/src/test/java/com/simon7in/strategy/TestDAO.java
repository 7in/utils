package com.simon7in.strategy;

import com.simon7in.strategy.dao.PlaybackRecordDAO;
import com.simon7in.strategy.entity.PlaybackRecordDO;
import com.taobao.itest.ITestSpringContextBaseCase;
import com.taobao.itest.annotation.ITestSpringContext;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by SIMON on 16/1/1.
 */
@ITestSpringContext({"classpath:dao-test.xml"})
public class TestDAO extends ITestSpringContextBaseCase {

    @Resource
    private PlaybackRecordDAO playbackRecordDAO;

    @Test
    public void selectTest() {
        PlaybackRecordDO query = new PlaybackRecordDO();
        query.setStartRow(20);
        query.setPageSize(20);
         List<PlaybackRecordDO> records = playbackRecordDAO.queryListObject(query);
        System.out.print("");
    }
}
