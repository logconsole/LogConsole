package info.logconsole.appender.test.logback;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xiahongjian 
 * @time   2018-04-10 10:07:33
 *
 */
public class LogbackAppenderTest {
	@Test
	public void testAppender() {
		Logger logger = LoggerFactory.getLogger(LogbackAppenderTest.class);
		logger.info("test info");
		logger.warn("test warn");
		logger.debug("test debug");
		logger.error("test error");
		logger.trace("test trace");
	}
}
