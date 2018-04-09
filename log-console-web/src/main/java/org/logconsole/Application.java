/**
 * File Name:Application.java
 * Package Name:org.logconsole
 * Date:2018年4月9日下午5:29:46
 * Copyright @ 2018-   All Rights Reserved.
 */

package org.logconsole;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ClassName:Application <br/>
 * Description: 启动类. <br/>
 * Date:     2018年4月9日 下午5:29:46 <br/>
 * @author   丁后刚
 * @version  
 * @since    JDK 1.7 
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
	}
}

