/**
 * Project Name:log-console-admin
 * File Name:LogMQReceiver.java
 * Package Name:info.logconsole.admin.mqreceiver
 * Date:2018年4月11日上午10:22:57
 * Copyright @ 2018- Young Man  All Rights Reserved.
 */

package info.logconsole.admin.mqreceiver;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import info.logconsole.appender.MQAppenderConsts;

/**
 * ClassName:LogMQReceiver <br/>
 * Description: 接收消息队列中log日志信息. <br/>
 * Date:     2018年4月11日 上午10:22:57 <br/>
 * @author   丁后刚
 */
@Component
public class LogMQReceiver {

	@JmsListener(destination = MQAppenderConsts.TOPIC)
	public void receiveMQLogMessage(String logMessage) {
		
		System.out.println("log message is ==> " + logMessage);
		
	}
}

