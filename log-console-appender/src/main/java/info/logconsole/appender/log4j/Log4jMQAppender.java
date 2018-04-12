/**
 * Project Name:log-console-appender
 * File Name:Log4jMQAppender.java
 * Package Name:info.logconsole.log4j
 * Date:2018年4月12日下午1:25:53
 */

package info.logconsole.appender.log4j;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.apache.log4j.net.JMSAppender;
import org.apache.log4j.spi.ErrorCode;
import org.apache.log4j.spi.LoggingEvent;

import info.logconsole.appender.model.LogMessage;

/**
 * ClassName:Log4jMQAppender <br/>
 * Description: 扩展log4j日志输出至MQ消息格式. <br/>
 * Date: 2018年4月12日 下午1:25:53 <br/>
 * 
 * @author 丁后刚
 */
public class Log4jMQAppender extends JMSAppender {
	/**
	 * 系统名称  标识系统
	 */
	String appName;

	@Override
	public void append(LoggingEvent event) {

		if (!checkEntryConditions()) {
			return;
		}

		try {
			TextMessage msg = this.getTopicSession().createTextMessage();
			if (this.getLocationInfo()) {
				event.getLocationInformation();
			}
			
			LogMessage logMessage = new LogMessage();
			logMessage.setAppName(appName);
			logMessage.setLevel(event.getLevel().toString());
			logMessage.setTimestamp(System.currentTimeMillis());
			logMessage.setLoggerName(event.getLoggerName());
			logMessage.setThreadName(event.getThreadName());
			logMessage.setLog(event.getMessage().toString());
			msg.setText(logMessage.toString());
			
			this.getTopicPublisher().publish(msg);
		} catch (JMSException e) {
			errorHandler.error("Could not publish message in JMSAppender [" + name + "].", e,
					ErrorCode.GENERIC_FAILURE);
		} catch (RuntimeException e) {
			errorHandler.error("Could not publish message in JMSAppender [" + name + "].", e,
					ErrorCode.GENERIC_FAILURE);
		}
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

}
