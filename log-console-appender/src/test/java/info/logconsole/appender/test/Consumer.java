package info.logconsole.appender.test;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import info.logconsole.appender.MQAppenderConsts;
import info.logconsole.appender.model.LogMessage;


/**
 * @author xiahongjian 
 * @time   2018-04-10 10:24:09
 *
 */
public class Consumer {
	public static void main(String[] args) {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, ActiveMQConnection.DEFAULT_BROKER_URL);
		try {
			Connection conn = factory.createConnection();
			conn.start();
			Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
//			Destination dest = session.createTopic(MQAppenderConsts.TOPIC);
			Destination dest = session.createQueue(MQAppenderConsts.QUQUE);
			MessageConsumer consumer = session.createConsumer(dest);
			consumer.setMessageListener(message -> {
				if (message instanceof ObjectMessage) {
					ObjectMessage objectMessage = (ObjectMessage) message;
					try {
						LogMessage log = (LogMessage) objectMessage.getObject();
						System.out.println(log);
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			});
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
