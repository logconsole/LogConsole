package info.logconsole.appender.logback;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.alibaba.fastjson.JSONObject;

import ch.qos.logback.classic.spi.ILoggingEvent;
import info.logconsole.appender.model.LogMessage;

/**
 * @author xiahongjian 
 * @time   2018-04-13 17:21:53
 */
public interface TextMessageCreator {
	default TextMessage createMessage(Session session, ILoggingEvent event, String appName, String host) throws JMSException {
		TextMessage message = session.createTextMessage();
		LogMessage logMessage = new LogMessage();
		logMessage.setAppName(appName);
		logMessage.setHost(host);
		logMessage.setLevel(event.getLevel().toString());
		logMessage.setTimestamp(event.getTimeStamp());
		logMessage.setLoggerName(event.getLoggerName());
		logMessage.setThreadName(event.getThreadName());
		logMessage.setLog(event.getFormattedMessage());
		StackTraceElement[] callerData = event.getCallerData();
		if (callerData != null && callerData.length > 0) {
			StackTraceElement ste = callerData[0];
			logMessage.setMethod(ste.getMethodName());
			logMessage.setClazz(ste.getClassName());
			logMessage.setLine(ste.getLineNumber());
		}
		message.setText(JSONObject.toJSONString(logMessage));
		return message;
	}
}
