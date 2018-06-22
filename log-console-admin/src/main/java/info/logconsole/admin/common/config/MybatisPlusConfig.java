package info.logconsole.admin.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;

import info.logconsole.admin.entity.handler.CommonMetaObjectHandler;

/**
 * ClassName: MybatisPlusConfig <br/>
 * Description: mybatis-plus配置. <br/>
 * date: 2018年4月9日 下午6:02:49 <br/>
 *
 * @author 丁后刚
 * @version 
 * @since JDK 1.8
 */
@Configuration
@MapperScan(basePackages={"info.logconsole.admin.mapper"})
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
    
    @Bean
    public CommonMetaObjectHandler commonMetaObjectHandler() {
        return new CommonMetaObjectHandler();
    }
    
}
