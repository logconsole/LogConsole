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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import info.logconsole.admin.entity.LogRecordInfo;
import info.logconsole.admin.entity.enums.LogLevel;
import info.logconsole.admin.event.events.LogReceivedEvent;
import info.logconsole.admin.service.LogRecordInfoService;
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
	
    @Autowired
    private ApplicationEventPublisher appEventPublisher;
    
	@Autowired
	private LogRecordInfoService logRecordInfoService;

//	@JmsListener(destination = MQAppenderConsts.TOPIC)
	public void receiveMQLogMessage(String logMessage) {
		LogMessage logMsg = JSONObject.parseObject(logMessage, LogMessage.class);
		
		LogRecordInfo logRecordInfo = new LogRecordInfo();
		logRecordInfo.setAppName(logMsg.getAppName());
		logRecordInfo.setClazz(logMsg.getClazz());
		logRecordInfo.setLogLevel(LogLevel.of(logMsg.getLevel()));
		logRecordInfo.setContent(logMsg.getLog());
		logRecordInfo.setCreateTime(LocalDateTime.now());
		logRecordInfo.setLogTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(logMsg.getTimestamp()), 
										ZoneId.systemDefault()));
		
		logRecordInfoService.insert(logRecordInfo);
		appEventPublisher.publishEvent(new LogReceivedEvent(LogReceivedEvent.NOTIFER_CHECK, logRecordInfo));
	}
}

