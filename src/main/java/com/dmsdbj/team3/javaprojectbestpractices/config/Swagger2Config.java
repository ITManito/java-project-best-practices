package com.dmsdbj.team3.javaprojectbestpractices.config;

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
 * @author : angel-rmm
 * @Date 2019/12/3  9:45 上午
 * @Package com.dmsdbj.team3.javaprojectbestpractices.config
 * @Version v1.0
 * @Copyright
 * @Email 18232466045@163.com
 * @Contract https://github.com/ITManito
 */
//通过@Configuration注解，让Spring来加载该类配置
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {
//        Docket的bean定义了Swagger的版本、需要暴露的API等信息
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.dmsdbj.team3.javaprojectbestpractices.controller")).paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("springboot利用swagger构建api文档-最佳实践-2019/12/3").version("1.0").build();
    }
}