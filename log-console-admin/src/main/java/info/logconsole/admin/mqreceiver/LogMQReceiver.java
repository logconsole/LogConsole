/**
 * Project Name:log-console-admin
 * File Name:LogMQReceiver.java
 * Package Name:info.logconsole.admin.mqreceiver
 * Date:2018年4月11日上午10:22:57
 * Copyright @ 2018- Young Man  All Rights Reserved.
 */

package info.logconsole.admin.mqreceiver;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.annotation.Resource;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import info.logconsole.admin.entity.LogRecordInfo;
import info.logconsole.admin.mapper.LogRecordInfoMapper;
import info.logconsole.appender.MQAppenderConsts;
import info.logconsole.appender.model.LogMessage;

/**
 * ClassName:LogMQReceiver <br/>
 * Description: 接收消息队列中log日志信息. <br/>
 * Date:     2018年4月11日 上午10:22:57 <br/>
 * @author   丁后刚
 */
@Component
public class LogMQReceiver {
	
	@Resource
	private LogRecordInfoMapper logRecordInfoMapper;

	@JmsListener(destination = MQAppenderConsts.QUEUE)
	public void receiveMQLogMessage(String logMessage) {
		LogMessage logMsg = JSONObject.parseObject(logMessage, LogMessage.class);
		
		LogRecordInfo logRecordInfo = new LogRecordInfo();
		logRecordInfo.setSystemId(logMsg.getAppName());
		logRecordInfo.setClazz(logMsg.getClazz());
		logRecordInfo.setLogLevel(logMsg.getLevel());
		logRecordInfo.setLogContent(logMsg.getLog());
		logRecordInfo.setCreateDate(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		logRecordInfo.setLogTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(logMsg.getTimestamp()), 
										ZoneId.systemDefault()));
		
		logRecordInfoMapper.insert(logRecordInfo);
	}
}

