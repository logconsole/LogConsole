package info.logconsole.appender;

/**
 * @author xiahongjian 
 * @time   2018-04-09 11:29:48
 *
 */
public interface MQAppenderConsts {
	/**
	 * 默认应用名称
	 */
	String DEFAULT_APP_NAME = "NO_NAME_APP";
	
	/**
	 * 默认应用服务器host
	 */
	String DEFAULT_HOST = "127.0.0.1";
	
	/**
	 * 默认消息队列topic名称
	 */
	String TOPIC = "logConsole.logTopic";
	
	/**
	 * 默认jndi中topic绑定名称
	 */
	String DEFAULT_TOPIC_BINDING_NAME = "logTopic";
	
	/**
	 * 默认消息队列queue名称
	 */
	String QUEUE = "logConsole.logQueue";
	
	/**
	 * 默认jndi中queue绑定名称
	 */
	String DEFAULT_QUEUE_BINDING_NAME = "logQueue";
	
	/**
	 * 默认jndi中topic connection factory绑定名称
	 */
	String DEFAULT_TOPIC_CONNECTION_FACTORY_BINDING_NAME = "ConnectionFactory";
}
