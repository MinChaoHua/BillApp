package com.bill.system.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
//@Profile(value = {"dev","test"})//spring.profiles: dev,test时才生效
@Configuration
@ConditionalOnProperty(value = "swagger.enable",havingValue = "true")//只有在配置文件中添加了swagger.enable=true的配置环境才启用swagger文档。
public class Swagger2Config {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //扫描的包
                .apis(RequestHandlerSelectors.basePackage("com.bill.system.controller"))
                // 选择API路径
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // swagger ui 的标题
                .title("Spring Boot中使用 Swagger2 构建 Restful APIs")
                // 描述
                .description("更多信息关注公众号")
                // 外链
                .termsOfServiceUrl("https://www.jianshu.com/u/008ce054774c")
                // 文档的版本信息
                .version("1.0")
                .build();
    }

}
