package info.logconsole.admin.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: ResourcesConfig <br/>
 * Description: 资源. <br/>
 * date: 2018年4月9日 下午6:03:24 <br/>
 *
 * @author 丁后刚
 * @version 
 * @since JDK 1.8
 */
@Configuration
public class ResourcesConfig implements WebMvcConfigurer {
	/**
	 * 静态资源映射
	 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/statics/**").addResourceLocations("classpath:/statics/");
    }
}
