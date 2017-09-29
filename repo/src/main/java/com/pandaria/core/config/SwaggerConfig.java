package com.pandaria.core.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Configuration
@EnableWebMvc
@EnableSwagger2
@ComponentScan(basePackages = {"com.pandaria.web"})
public class SwaggerConfig {

    @Bean
    public Docket webApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.POST, Lists.newArrayList(
                        new ResponseMessageBuilder().code(200).message("请求成功！").build(),
                        new ResponseMessageBuilder().code(400).message("请求参数出错！").build()
                ))
                .groupName("1-end").apiInfo(apiInfo())
                .forCodeGeneration(true)
                .ignoredParameterTypes(ModelMap.class, HttpSession.class, Map.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.pandaria"))
                //.apis(RequestHandlerSelectors.any())
                //.paths(PathSelectors.regex(""))
                // .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .apis(handlersForEnd())
                //.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.pandaria.web.sys")))
                //.paths(excludePath("admin/"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket adminApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.POST, Lists.newArrayList(
                        new ResponseMessageBuilder().code(200).message("请求成功！").build(),
                        new ResponseMessageBuilder().code(400).message("请求参数出错！").build()
                ))
                .groupName("2-admin").apiInfo(apiInfo())
                .forCodeGeneration(true)
                .ignoredParameterTypes(ModelMap.class, HttpSession.class, Map.class)
                .select()
                //.apis(RequestHandlerSelectors.any())
                //.paths(PathSelectors.regex(""))
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .apis(handlersForAdmin())
                //.paths(PathSelectors.regex("/admin.*"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("项目名称").description("©2017 Copyright. Powered By 用户名.")
                // .termsOfServiceUrl("")
                .contact(new Contact("contactName", "", "userName@163.com")).license("Apache License Version 2.0")
                .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE").version("2.0").build();
    }

    private Predicate<RequestHandler> handlersForEnd() {
        return Predicates.or(
                RequestHandlerSelectors.basePackage("com.pandaria.web.end")
        );
    }

    private Predicate<RequestHandler> handlersForAdmin() {
        return Predicates.or(
                RequestHandlerSelectors.basePackage("com.pandaria.web.admin")
        );
    }

}