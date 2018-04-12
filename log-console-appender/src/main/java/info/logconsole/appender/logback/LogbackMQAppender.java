package info.logconsole.appender.logback;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

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
	
	private ObjectMessage createMessage(ILoggingEvent event) {
		try {
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
		} catch (JMSException e) {
			addStatus(new ErrorStatus(e.getMessage(), this));
		}
		return null;
	}
}
