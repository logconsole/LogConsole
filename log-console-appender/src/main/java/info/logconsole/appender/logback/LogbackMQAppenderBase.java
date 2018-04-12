package info.logconsole.appender.logback;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

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

	protected ConnectionFactory factory;
	protected Connection conn;
	protected Session session;
	protected Destination dest;
	protected MessageProducer producer;

	@Override
	public void start() {
		int errors = 0;
		if (this.encoder == null) {
			addStatus(new ErrorStatus("No encoder set for the appender named \"" + name + "\".", this));
			errors++;
		}
		factory = new ActiveMQConnectionFactory(username, password, brokerURL);
		try {
			conn = factory.createConnection();
			session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			dest = session.createTopic(topic);
			producer = session.createProducer(dest);
			conn.start();
		} catch (JMSException e) {
			addStatus(new ErrorStatus("Failed to connect to mq using username:" + username + ", password:" + password
					+ ", brokderURL:" + brokerURL + " for appender named \"" + name + "\".", this));
			errors++;
		}
		if (errors == 0)
			super.start();
	}

	@Override
	public void stop() {
		try {
			if (producer != null)
				producer.close();
			if (session != null)
				session.close();
			if (conn != null)
				conn.close();
		} catch (JMSException e) {
			addStatus(new ErrorStatus("Failed to close the mq connection for appender named \"" + name + "\".", this));
		}
		producer = null;
		dest = null;
		session = null;
		conn = null;
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

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
}
