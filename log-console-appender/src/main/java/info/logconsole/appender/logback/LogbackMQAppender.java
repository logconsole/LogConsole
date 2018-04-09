package info.logconsole.appender.logback;

import javax.jms.ObjectMessage;

import ch.qos.logback.classic.spi.ILoggingEvent;
import info.logconsole.appender.model.LogMessage;

/**
 * @author xiahongjian
 * @time 2018-04-09 09:13:00
 *
 */
public class LogbackMQAppender extends LogbackMQAppenderBase {

	@Override
	protected void append(ILoggingEvent event) {
		jmsTemplate.send(session -> {
			ObjectMessage message = session.createObjectMessage();
			LogMessage logMessage = new LogMessage();
			logMessage.setAppName(getAppName());
			logMessage.setLevel(event.getLevel().toString());
			logMessage.setTimestamp(System.currentTimeMillis());
			logMessage.setLoggerName(event.getLoggerName());
			logMessage.setThreadName(event.getThreadName());
			logMessage.setLog(new String(getEncoder().encode(event)));
			message.setObject(logMessage);
			return message;
		});
	}
}
