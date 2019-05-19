package com.bill.system.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

    /**
     * ctrl+o 出现可重写的方法
     * */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

        /* 视图跳转控制器 */
        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/login").setViewName("login");
            registry.addViewController("/calendar.html").setViewName("calendar");
            registry.addViewController("/chart.html").setViewName("chart");
            registry.addViewController("/form.html").setViewName("form");
            registry.addViewController("/sign-up.html").setViewName("sign-up");
            registry.addViewController("/table-list.html").setViewName("table-list");
            registry.addViewController("/table-list-img.html").setViewName("table-list-img");
            registry.addViewController("/tables.html").setViewName("tables");
            registry.addViewController("/404.html").setViewName("404");
            registry.addViewController("/bill-setting.html").setViewName("bill-type");

        }
        /* 拦截器配置 ,注册拦截器*/
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new MyHandlerInterceptor()).addPathPatterns("/**")
                    .excludePathPatterns("/login","/login.html","/toRegister","/tologin","/static/**");

        }
//    /* 视图解析器 */
//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//
//    }

    // 所有的WebMvcConfigurerAdapter组件都会一起起作用
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/login.html").setViewName("login");
                registry.addViewController("/index.html").setViewName("index");
                registry.addViewController("/index").setViewName("index");

            }
        };
        return webMvcConfigurer;
    }
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}

