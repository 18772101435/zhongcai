package com.silv.api.config;

import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2的Java配置类：配置接口文档页面swagger-ui.html的一些展示信息
 *
 * @author 刘阳
 */
@Configuration//让SpringBoot来加载该类配置
@EnableSwagger2//启用Swagger2
public class Swagger2Config {

    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .produces(Sets.newHashSet("application/json"))
                .consumes(Sets.newHashSet("application/json"))
                .protocols(Sets.newHashSet("http", "https"))
                .apiInfo(apiInfo())
                .forCodeGeneration(true)
                .select()
                // 指定controller类的扫描范围
                .apis(RequestHandlerSelectors.basePackage("com.silv.api.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {

        Contact contact = new Contact("陈瑶", "https://www.hao123.com/", "617807685@qq.com");

        return new ApiInfoBuilder()
                .title("自己的项目")// 文档标题
                .description("自己的项目使用swagger")// 文档描述
                .contact(contact)// 添加联系人信息
                // .termsOfServiceUrl("https://github.com/yidao620c")
                .version("v1")
                // .license("MIT 协议")
                // .licenseUrl("http://www.opensource.org/licenses/MIT")
                // .contact(new Contact("熊能","https://github.com/yidao620c","yidao620@gmail.com"))
                .build();
    }
}