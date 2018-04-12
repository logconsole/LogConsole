package info.logconsole.appender.logback;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import com.alibaba.fastjson.JSONObject;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.status.ErrorStatus;
import info.logconsole.appender.model.LogMessage;

/**
 * @author xiahongjian
 * @time 2018-04-09 09:13:00
 *
 */
public class LogbackMQAppender extends LogbackMQAppenderBase {

	@Override
	protected void append(ILoggingEvent event) {
		try {
			producer.send(createMessage(event));
		} catch (JMSException e) {
			addStatus(new ErrorStatus(e.getMessage(), this));
		}
	}
	
	private TextMessage createMessage(ILoggingEvent event) {
		try {
			TextMessage message = session.createTextMessage();
			LogMessage logMessage = new LogMessage();
			logMessage.setAppName(getAppName());
			logMessage.setHost(getHost());
			logMessage.setLevel(event.getLevel().toString());
			logMessage.setTimestamp(System.currentTimeMillis());
			logMessage.setLoggerName(event.getLoggerName());
			logMessage.setThreadName(event.getThreadName());
			logMessage.setLog(event.getFormattedMessage());
			message.setText(JSONObject.toJSONString(logMessage));;
			return message;
		} catch (JMSException e) {
			addStatus(new ErrorStatus(e.getMessage(), this));
		}
		return null;
	}
}
