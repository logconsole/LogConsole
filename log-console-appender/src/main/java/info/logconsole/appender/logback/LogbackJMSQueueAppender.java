package info.logconsole.appender.logback;

import java.io.Serializable;

import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;

import ch.qos.logback.classic.net.LoggingEventPreSerializationTransformer;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.spi.PreSerializationTransformer;

/**
 * @author xiahongjian 
 * @time   2018-04-13 10:28:51
 *
 */
public class LogbackJMSQueueAppender extends LogbackJMSAppenderBase<ILoggingEvent> {
	static int SUCCESSIVE_FAILURE_LIMIT = 3;

    String queueBindingName;
    String qcfBindingName;
    QueueConnection queueConnection;
    QueueSession queueSession;
    QueueSender queueSender;

    int successiveFailureCount = 0;

    private PreSerializationTransformer<ILoggingEvent> pst = new LoggingEventPreSerializationTransformer();

    /**
     * The <b>QueueConnectionFactoryBindingName</b> option takes a string value.
     * Its value will be used to lookup the appropriate
     * <code>QueueConnectionFactory</code> from the JNDI context.
     */
    public void setQueueConnectionFactoryBindingName(String qcfBindingName) {
        this.qcfBindingName = qcfBindingName;
    }

    /**
     * Returns the value of the <b>QueueConnectionFactoryBindingName</b> option.
     */
    public String getQueueConnectionFactoryBindingName() {
        return qcfBindingName;
    }

    /**
     * The <b>QueueBindingName</b> option takes a string value. Its value will be
     * used to lookup the appropriate <code>Queue</code> from the JNDI context.
     */
    public void setQueueBindingName(String queueBindingName) {
        this.queueBindingName = queueBindingName;
    }

    /**
     * Returns the value of the <b>QueueBindingName</b> option.
     */
    public String getQueueBindingName() {
        return queueBindingName;
    }

    /**
     * Options are activated and become effective only after calling this method.
     */
    public void start() {
        QueueConnectionFactory queueConnectionFactory;

        try {
            Context jndi = buildJNDIContext();

            // addInfo("Looking up [" + qcfBindingName + "]");
            queueConnectionFactory = (QueueConnectionFactory) lookup(jndi, qcfBindingName);
            // addInfo("About to create QueueConnection.");
            if (userName != null) {
                this.queueConnection = queueConnectionFactory.createQueueConnection(userName, password);
            } else {
                this.queueConnection = queueConnectionFactory.createQueueConnection();
            }

            // addInfo(
            // "Creating QueueSession, non-transactional, "
            // + "in AUTO_ACKNOWLEDGE mode.");
            this.queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

            // addInfo("Looking up queue name [" + queueBindingName + "].");
            Queue queue = (Queue) lookup(jndi, queueBindingName);

            // addInfo("Creating QueueSender.");
            this.queueSender = queueSession.createSender(queue);

            // addInfo("Starting QueueConnection.");
            queueConnection.start();

            jndi.close();
        } catch (Exception e) {
            addError("Error while activating options for appender named [" + name + "].", e);
        }

        if (this.queueConnection != null && this.queueSession != null && this.queueSender != null) {
            super.start();
        }
    }

    /**
     * Close this JMSAppender. Closing releases all resources used by the
     * appender. A closed appender cannot be re-opened.
     */
    public synchronized void stop() {
        // The synchronized modifier avoids concurrent append and close operations
        if (!this.started) {
            return;
        }

        this.started = false;

        try {
            if (queueSession != null) {
                queueSession.close();
            }
            if (queueConnection != null) {
                queueConnection.close();
            }
        } catch (Exception e) {
            addError("Error while closing JMSAppender [" + name + "].", e);
        }

        // Help garbage collection
        queueSender = null;
        queueSession = null;
        queueConnection = null;
    }

    /**
     * This method called by {@link AppenderBase#doAppend} method to do most
     * of the real appending work.
     */
    public void append(ILoggingEvent event) {
        if (!isStarted()) {
            return;
        }

        try {
            ObjectMessage msg = queueSession.createObjectMessage();
            Serializable so = pst.transform(event);
            msg.setObject(so);
            queueSender.send(msg);
            successiveFailureCount = 0;
        } catch (Exception e) {
            successiveFailureCount++;
            if (successiveFailureCount > SUCCESSIVE_FAILURE_LIMIT) {
                stop();
            }
            addError("Could not send message in JMSQueueAppender [" + name + "].", e);

        }
    }

    /**
     * Returns the QueueConnection used for this appender. Only valid after
     * start() method has been invoked.
     */
    protected QueueConnection getQueueConnection() {
        return queueConnection;
    }

    /**
     * Returns the QueueSession used for this appender. Only valid after start()
     * method has been invoked.
     */
    protected QueueSession getQueueSession() {
        return queueSession;
    }

    /**
     * Returns the QueueSender used for this appender. Only valid after start()
     * method has been invoked.
     */
    protected QueueSender getQueueSender() {
        return queueSender;
    }
}
