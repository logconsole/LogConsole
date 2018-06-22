package info.logconsole.admin.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import info.logconsole.admin.entity.LogNotifer;
import info.logconsole.admin.entity.enums.NotiferType;
import info.logconsole.admin.service.LogNotiferService;
import info.logconsole.admin.test.BaseTest;

/**
 * @author xiahongjian
 * @time 2018-06-22 05:40:56
 */

public class LogNotiferTest extends BaseTest {
    @Autowired
    private LogNotiferService logNotiferService;

    @Test
    public void testInsert() {
        LogNotifer notifer = new LogNotifer();
        notifer.setName("TestNotifer");
        notifer.setEnable(Boolean.TRUE);
        notifer.setType(NotiferType.EMAIL);
        notifer.setReceiver("hongjian.xia@qq.com");
        
        logNotiferService.insert(notifer);
    }
    
    @Test
    public void testUpdate() {
        LogNotifer notifer = logNotiferService.selectById(3);
        notifer.setEnable(Boolean.FALSE);
        logNotiferService.updateById(notifer);
    }
}
