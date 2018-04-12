/**
 * Project Name:log-console-appender
 * File Name:Main.java
 * Package Name:info.logconsole.appender.main
 * Date:2018年4月12日下午2:38:26
 * Copyright @ 2018- Young Man  All Rights Reserved.
 */

package info.logconsole.appender.main;

import info.logconsole.appender.task.LogProducer;

/**
 * ClassName:Main <br/>
 * Description: TODO Description. <br/>
 * Date:     2018年4月12日 下午2:38:26 <br/>
 * @author   丁后刚
 * @version  
 * @since    JDK 1.8 
 */
public class Main {

	public static void main(String[] args) {
		
		new Thread(new LogProducer()).start();
	}
}

