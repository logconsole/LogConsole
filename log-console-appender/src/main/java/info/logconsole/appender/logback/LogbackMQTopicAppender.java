package info.logconsole.appender.logback;

import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnection;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.status.ErrorStatus;
import info.logconsole.appender.MQAppenderConsts;

/**
 * logback基于ActiveMQ pub/sub消息模式实现的Appender
 * 
 * <p>
 * 可配置熟悉
 * </p>
 * <ul>
 * <li>appName: 应用名称</li>
 * <li>host: 应用服务器host</li>
 * <li>providerURL: 消息队列broker URL</li>
 * <li>userName: 消息队列用户名</li>
 * <li>password: 消息队列密码</li>
 * <li>topicConnectionFactoryBindingName:
 * JNDI中绑定<code>TopicConnectionFactory</code>对象的名称</li>
 * <li>topicBindingName: JNDI中绑定需要发送到消息队列Topic的名称</li>
 * </ul>
 * 
 * @author xiahongjian
 * @time 2018-04-09 09:13:00
 */
public class LogbackMQTopicAppender extends LogbackJMSTopicAppender implements TextMessageCreator {
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
			getTopicPublisher().send(createMessage(getTopicSession(), event, getAppName(), getHost()));
		} catch (JMSException e) {
			addStatus(new ErrorStatus(
					"Failed to send log to mq in logger named \"" + name + "\", because of: " + e.getMessage() + ".",
					this));
		}
	}

}
