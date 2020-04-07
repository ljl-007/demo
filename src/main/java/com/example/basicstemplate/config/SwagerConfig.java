package com.example.basicstemplate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwagerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // Controller 所在包
                .apis(RequestHandlerSelectors.basePackage("com.example.basicstemplate.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 接口文档的名字
                .title("测试类Api接口文档")
                // 接口文档的描述
                .description("Api 接口文档")
                // 服务条款的网址
                .termsOfServiceUrl("http://localhost/")
                // 接口文档的版本
                .version("1.0.0")
                // 接口文档维护联系的信息
//                .contact(new Contact("ljl", "", "1524275389@qq.com"))
                .build();
    }


}
