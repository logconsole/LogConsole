package info.logconsole.appender.logback;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import ch.qos.logback.core.encoder.Encoder;
import ch.qos.logback.core.status.ErrorStatus;
import info.logconsole.appender.MQAppenderConsts;

/**
 * @author xiahongjian
 * @time 2018-04-09 10:23:57
 *
 */
public abstract class LogbackMQAppenderBase extends UnsynchronizedAppenderBase<ILoggingEvent> {
	private Encoder<ILoggingEvent> encoder;

	private String appName = "NOT_SET";
	private String topic = MQAppenderConsts.TOPIC;
	private String username = ActiveMQConnection.DEFAULT_USER;
	private String password = ActiveMQConnection.DEFAULT_PASSWORD;
	private String brokerURL = ActiveMQConnection.DEFAULT_BROKER_URL;
	private int maxConnctions = 10;
	private int sessionCacheSize = 2;

	protected JmsTemplate jmsTemplate;
	private CachingConnectionFactory factory;

	@Override
	public void start() {
		int errors = 0;
		if (this.encoder == null) {
			addStatus(new ErrorStatus("No encoder set for the appender named \"" + name + "\".", this));
			errors++;
		}
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(username, password,
				brokerURL);
		PooledConnectionFactory poolConnectionFactory = new PooledConnectionFactory(activeMQConnectionFactory);
		poolConnectionFactory.setMaxConnections(maxConnctions);
		factory = new CachingConnectionFactory(poolConnectionFactory);
		factory.setSessionCacheSize(sessionCacheSize);
		jmsTemplate = new JmsTemplate(factory);
		jmsTemplate.setPubSubDomain(true);
		jmsTemplate.setDefaultDestinationName(MQAppenderConsts.TOPIC);
		if (errors == 0)
			super.start();
	}
	
	@Override
	public void stop() {
		factory.destroy();
		super.stop();
	}


	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Encoder<ILoggingEvent> getEncoder() {
		return encoder;
	}

	public void setEncoder(Encoder<ILoggingEvent> encoder) {
		this.encoder = encoder;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBrokerURL() {
		return brokerURL;
	}

	public void setBrokerURL(String brokerURL) {
		this.brokerURL = brokerURL;
	}

	public int getMaxConnctions() {
		return maxConnctions;
	}

	public void setMaxConnctions(int maxConnctions) {
		this.maxConnctions = maxConnctions;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
}
