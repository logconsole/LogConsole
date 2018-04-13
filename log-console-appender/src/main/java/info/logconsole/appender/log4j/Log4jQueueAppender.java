/**
 * Project Name:log-console-appender
 * File Name:Log4jQueueAppender.java
 * Package Name:info.logconsole.appender.log4j
 * Date:2018年4月13日上午10:12:27
 * Copyright @ 2018- Young Man  All Rights Reserved.
 */

package info.logconsole.appender.log4j;

import java.util.Properties;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.ErrorCode;
import org.apache.log4j.spi.LoggingEvent;
import com.alibaba.fastjson.JSONObject;
import info.logconsole.appender.model.LogMessage;

/**
 * ClassName:Log4jQueueAppender <br/>
 * Description: 定义输出至消息队列-Queue. <br/>
 * Date: 2018年4月13日 上午10:12:27 <br/>
 * 
 * @author 丁后刚
 * @version
 * @since JDK 1.8
 */
public class Log4jQueueAppender extends AppenderSkeleton {

	String securityPrincipalName;
	String securityCredentials;
	String initialContextFactoryName;
	String urlPkgPrefixes;
	String providerURL;
	String queueBindingName;
	String tcfBindingName;
	String userName;
	String password;
	boolean locationInfo;

	QueueConnection queueConnection;
	QueueSession queueSession;
	QueueSender queueSender;

	/**
	 * 系统名称 标识系统
	 */
	String appName;

	public Log4jQueueAppender() {
		super();
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public void setQueueConnectionFactoryBindingName(String tcfBindingName) {
		this.tcfBindingName = tcfBindingName;
	}

	public String getQueueConnectionFactoryBindingName() {
		return tcfBindingName;
	}

	public String getSecurityPrincipalName() {
		return securityPrincipalName;
	}

	public void setSecurityPrincipalName(String securityPrincipalName) {
		this.securityPrincipalName = securityPrincipalName;
	}

	public String getSecurityCredentials() {
		return securityCredentials;
	}

	public void setSecurityCredentials(String securityCredentials) {
		this.securityCredentials = securityCredentials;
	}

	public String getInitialContextFactoryName() {
		return initialContextFactoryName;
	}

	public void setInitialContextFactoryName(String initialContextFactoryName) {
		this.initialContextFactoryName = initialContextFactoryName;
	}

	public String getUrlPkgPrefixes() {
		return urlPkgPrefixes;
	}

	public void setUrlPkgPrefixes(String urlPkgPrefixes) {
		this.urlPkgPrefixes = urlPkgPrefixes;
	}

	public String getProviderURL() {
		return providerURL;
	}

	public void setProviderURL(String providerURL) {
		this.providerURL = providerURL;
	}

	public String getQueueBindingName() {
		return queueBindingName;
	}

	public void setQueueBindingName(String queueBindingName) {
		this.queueBindingName = queueBindingName;
	}

	public String getTcfBindingName() {
		return tcfBindingName;
	}

	public void setTcfBindingName(String tcfBindingName) {
		this.tcfBindingName = tcfBindingName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getLocationInfo() {
		return locationInfo;
	}

	public void setLocationInfo(boolean locationInfo) {
		this.locationInfo = locationInfo;
	}

	public QueueConnection getQueueConnection() {
		return queueConnection;
	}

	public void setQueueConnection(QueueConnection queueConnection) {
		this.queueConnection = queueConnection;
	}

	public QueueSession getQueueSession() {
		return queueSession;
	}

	public void setQueueSession(QueueSession queueSession) {
		this.queueSession = queueSession;
	}

	public QueueSender getQueueSender() {
		return queueSender;
	}

	public void setQueueSender(QueueSender queueSender) {
		this.queueSender = queueSender;
	}

	@Override
	public void activateOptions() {

		QueueConnectionFactory queueConnectionFactory;

		try {
			Context jndi;

			LogLog.debug("Getting initial context.");
			if (initialContextFactoryName != null) {
				Properties env = new Properties();
				env.put(Context.INITIAL_CONTEXT_FACTORY, initialContextFactoryName);
				if (providerURL != null) {
					env.put(Context.PROVIDER_URL, providerURL);
				} else {
					LogLog.warn("You have set InitialContextFactoryName option but not the "
							+ "ProviderURL. This is likely to cause problems.");
				}
				if (urlPkgPrefixes != null) {
					env.put(Context.URL_PKG_PREFIXES, urlPkgPrefixes);
				}

				if (securityPrincipalName != null) {
					env.put(Context.SECURITY_PRINCIPAL, securityPrincipalName);
					if (securityCredentials != null) {
						env.put(Context.SECURITY_CREDENTIALS, securityCredentials);
					} else {
						LogLog.warn("You have set SecurityPrincipalName option but not the "
								+ "SecurityCredentials. This is likely to cause problems.");
					}
				}
				jndi = new InitialContext(env);
			} else {
				jndi = new InitialContext();
			}

			LogLog.debug("Looking up [" + tcfBindingName + "]");
			queueConnectionFactory = (QueueConnectionFactory) lookup(jndi, tcfBindingName);
			LogLog.debug("About to create QueueConnection.");
			if (userName != null) {
				queueConnection = queueConnectionFactory.createQueueConnection(userName, password);
			} else {
				queueConnection = queueConnectionFactory.createQueueConnection();
			}

			LogLog.debug("Creating QueueSession, non-transactional, " + "in AUTO_ACKNOWLEDGE mode.");
			queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

			LogLog.debug("Looking up queue name [" + queueBindingName + "].");
			Queue queue = (Queue) lookup(jndi, queueBindingName);

			LogLog.debug("Creating QueueSender.");
			queueSender = queueSession.createSender(queue);

			LogLog.debug("Starting QueueConnection.");
			queueConnection.start();

			jndi.close();
		} catch (JMSException e) {
			errorHandler.error("Error while activating options for appender named [" + name + "].", e,
					ErrorCode.GENERIC_FAILURE);
		} catch (NamingException e) {
			errorHandler.error("Error while activating options for appender named [" + name + "].", e,
					ErrorCode.GENERIC_FAILURE);
		} catch (RuntimeException e) {
			errorHandler.error("Error while activating options for appender named [" + name + "].", e,
					ErrorCode.GENERIC_FAILURE);
		}
	}

	protected Object lookup(Context ctx, String name) throws NamingException {
		try {
			return ctx.lookup(name);
		} catch (NameNotFoundException e) {
			LogLog.error("Could not find name [" + name + "].");
			throw e;
		}
	}

	protected boolean checkEntryConditions() {
		String fail = null;

		if (this.queueConnection == null) {
			fail = "No QueueConnection";
		} else if (this.queueSession == null) {
			fail = "No QueueSession";
		} else if (this.queueSender == null) {
			fail = "No QueueSender";
		}

		if (fail != null) {
			errorHandler.error(fail + " for Log4jQueueAppender named [" + name + "].");
			return false;
		} else {
			return true;
		}
	}

	@Override
	public synchronized void close() {

		if (this.closed)
			return;

		LogLog.debug("Closing appender [" + name + "].");
		this.closed = true;

		try {
			if (queueSession != null)
				queueSession.close();
			if (queueConnection != null)
				queueConnection.close();
		} catch (JMSException e) {
			LogLog.error("Error while closing Log4jQueueAppender [" + name + "].", e);
		} catch (RuntimeException e) {
			LogLog.error("Error while closing Log4jQueueAppender [" + name + "].", e);
		}
		// Help garbage collection
		queueSender = null;
		queueSession = null;
		queueConnection = null;

	}

	@Override
	public boolean requiresLayout() {

		return false;
	}

	@Override
	public void append(LoggingEvent event) {

		if (!checkEntryConditions()) {
			return;
		}

		try {
			TextMessage msg = this.getQueueSession().createTextMessage();
			if (this.getLocationInfo()) {
				event.getLocationInformation();
			}

			LogMessage logMessage = new LogMessage();
			logMessage.setAppName(appName);
			logMessage.setLevel(event.getLevel().toString());
			logMessage.setTimestamp(System.currentTimeMillis());
			logMessage.setLoggerName(event.getLoggerName());
			logMessage.setThreadName(event.getThreadName());
			logMessage.setLog(event.getMessage().toString());
			msg.setText(JSONObject.toJSONString(logMessage));

			this.getQueueSender().send(msg);
		} catch (JMSException e) {
			errorHandler.error("Could not publish message in Log4jQueueAppender [" + name + "].", e,
					ErrorCode.GENERIC_FAILURE);
		} catch (RuntimeException e) {
			errorHandler.error("Could not publish message in Log4jQueueAppender [" + name + "].", e,
					ErrorCode.GENERIC_FAILURE);
		}
	}

}
