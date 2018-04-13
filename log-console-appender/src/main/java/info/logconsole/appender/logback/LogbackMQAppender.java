package info.logconsole.appender.logback;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;

import com.alibaba.fastjson.JSONObject;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.status.ErrorStatus;
import info.logconsole.appender.MQAppenderConsts;
import info.logconsole.appender.model.LogMessage;

/**
 * @author xiahongjian
 * @time 2018-04-09 09:13:00
 *
 */
public class LogbackMQAppender extends LogbackJMSTopicAppender {
	/**
	 * 系统名称
	 */
	private String appName = MQAppenderConsts.DEFAULT_APP_NAME;
	/**
	 * 应用服务器host
	 */
	private String host = MQAppenderConsts.DEFAULT_HOST;

	{
		// set the default properties
		setTopicConnectionFactoryBindingName(MQAppenderConsts.DEFAULT_TOPIC_CONNECTION_FACTORY_BINDING_NAME);
		setTopicBindingName(MQAppenderConsts.DEFAULT_TOPIC_BINDING_NAME);
		setProviderURL(ActiveMQConnection.DEFAULT_BROKER_URL);
		setUserName(ActiveMQConnection.DEFAULT_USER);
		setPassword(ActiveMQConnection.DEFAULT_PASSWORD);
	}

	@Override
	public void append(ILoggingEvent event) {
		try {
			getTopicPublisher().send(createMessage(event));
		} catch (JMSException e) {
			addStatus(new ErrorStatus(e.getMessage(), this));
		}
	}

	private TextMessage createMessage(ILoggingEvent event) {
		try {
			TextMessage message = getTopicSession().createTextMessage();
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
		} catch (JMSException e) {
			addStatus(new ErrorStatus(e.getMessage(), this));
		}
		return null;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
}
