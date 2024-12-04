package com.fqh.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger2自动配置--根据服务名分组
 *
 * @author fqh
 * @date 2022/08/14
 */
@Configuration
@EnableSwagger2
public class Swagger2AutoConfig {

    @Value("spring.application.name")
    private String serverName;
    /**
     * 创建API应用
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fqh.**.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     * @return
     */

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(serverName+"接口文档")
                .description("商城系统相关服务")
                //.termsOfServiceUrl("http://www.baidu.com")
                .version("1.0")
                .build();
    }



}