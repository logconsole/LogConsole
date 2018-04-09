package info.logconsole.appender.model;

import java.io.Serializable;

/**
 * @author xiahongjian 
 * @time   2018-04-09 10:34:22
 *
 */
public class LogMessage implements Serializable {
	private static final long serialVersionUID = -8907492893056876722L;
	private String appName;
	private String host;
	private String level;
	private String loggerName;
	private String threadName;
	private String clazz;
	private String method;
	private Integer line;
	private String log;
	private long timestamp;
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getLoggerName() {
		return loggerName;
	}
	public void setLoggerName(String loggerName) {
		this.loggerName = loggerName;
	}
	public String getThreadName() {
		return threadName;
	}
	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Integer getLine() {
		return line;
	}
	public void setLine(Integer line) {
		this.line = line;
	}
	@Override
	public String toString() {
		return "LogMessage [appName=" + appName + ", host=" + host + ", level=" + level + ", loggerName=" + loggerName
				+ ", threadName=" + threadName + ", clazz=" + clazz + ", method=" + method + ", line=" + line + ", log="
				+ log + ", timestamp=" + timestamp + "]";
	}

}
