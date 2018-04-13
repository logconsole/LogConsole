package info.logconsole.appender.logback;

import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnection;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.status.ErrorStatus;
import info.logconsole.appender.MQAppenderConsts;

/**
 * <p>
 * logback基于ActiveMQ点对点模式实现的Appender
 * </p>
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
 * <li>queueConnectionFactoryBindingName:
 * JNDI中绑定<code>QueueConnectionFactory</code>对象的名称</li>
 * <li>queueBindingName: JNDI中绑定需要发送到消息队列Queue的名称</li>
 * </ul>
 * 
 * @author xiahongjian
 * @time 2018-04-09 09:13:00
 */
public class LogbackMQQueueAppender extends LogbackJMSQueueAppender implements TextMessageCreator {

	{
		// set the default properties
		setQueueConnectionFactoryBindingName(MQAppenderConsts.DEFAULT_QUEUE_CONNECTION_FACTORY_BINDING_NAME);
		setQueueBindingName(MQAppenderConsts.DEFAULT_QUEUE_BINDING_NAME);
		setProviderURL(ActiveMQConnection.DEFAULT_BROKER_URL);
		setUserName(ActiveMQConnection.DEFAULT_USER);
		setPassword(ActiveMQConnection.DEFAULT_PASSWORD);
	}

	@Override
	public void append(ILoggingEvent event) {
		try {
			getQueueSender().send(createMessage(getQueueSession(), event, getAppName(), getHost()));
		} catch (JMSException e) {
			addStatus(new ErrorStatus(
					"Failed to send log to mq in logger named \"" + name + "\", because of: " + e.getMessage() + ".",
					this));
		}
	}
}
