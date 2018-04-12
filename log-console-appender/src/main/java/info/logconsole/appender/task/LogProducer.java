/**
 * Project Name:log-appender
 * File Name:LogProducer.java
 * Package Name:info.logconsole.task
 * Date:2018年4月12日下午2:34:16
 */

package info.logconsole.appender.task;

import org.apache.log4j.Logger;

/**
 * ClassName:LogProducer <br/>
 * Description: 生产测试日志. <br/>
 * Date:     2018年4月12日 下午2:34:16 <br/>
 * @author   丁后刚
 * @version  
 * @since    JDK 1.8 
 */
public class LogProducer implements Runnable {
	
	private final static Logger log = Logger.getLogger(LogProducer.class);

	public void run() {
		int sum = 0;
		
		while(true) {
			//sum++;
			log.info("logproducer demo ...  ==> " + sum);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
			}
		}
		
	}

}

