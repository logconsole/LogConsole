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
 * logback基于ActiveMQ pub/sub消息模式实现的Appender
 * 
 * <p>可配置熟悉</p>
 * <ul>
 * 	<li>appName: 应用名称</li>
 * 	<li>host: 应用服务器host</li>
 * 	<li>providerURL: 消息队列broker URL</li>
 * 	<li>userName: 消息队列用户名</li>
 * 	<li>password: 消息队列密码</li>
 *  <li>topicConnectionFactoryBindingName: JNDI中绑定<code>TopicConnectionFactory</code>对象的名称</li>
 *  <li>topicBindingName: JNDI中绑定需要发送到消息队列Topic的名称</li>
 * </ul>
 * 
 * @author xiahongjian
 * @time 2018-04-09 09:13:00
 */
public class LogbackMQTopicAppender extends LogbackJMSTopicAppender {
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
			getTopicPublisher().send(createMessage(event, getTopicSession().createTextMessage()));
		} catch (JMSException e) {
			addStatus(new ErrorStatus(e.getMessage(), this));
		}
	}

	private TextMessage createMessage(ILoggingEvent event, TextMessage message) {
		try {
			LogMessage logMessage = new LogMessage();
			logMessage.setAppName(getAppName());
			logMessage.setHost(getHost());
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


}
