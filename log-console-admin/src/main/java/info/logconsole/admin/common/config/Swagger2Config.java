package info.logconsole.admin.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author xiahongjian
 * @time 2018-06-20 22:40:01
 */
@Configuration
@EnableSwagger2
public class Swagger2Config implements WebMvcConfigurer {
    private static final String BASE_PACKAGE = "info.logconsole.admin.controller";

    // 解决由配置spring.mvc.static-path-pattern=/static/** 导致的swagger-ui.html找不到的问题
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars*")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.ant("/admin/**")).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("LonConsole API文档")
                .description("swagger文档")
                .termsOfServiceUrl("www.logconsole.info")
                .contact(new Contact("xiahongjian", "", "hongjian.xia@qq.com"))
                .version("1.0.0")
                .build();
    }
}
