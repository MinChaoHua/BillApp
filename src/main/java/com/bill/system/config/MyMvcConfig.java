package com.bill.system.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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
            registry.addViewController("/index.html").setViewName("index");
            registry.addViewController("/sign-up.html").setViewName("sign-up");
            registry.addViewController("/table-list.html").setViewName("table-list");
            registry.addViewController("/updatePassword.html").setViewName("updatePassword");
            registry.addViewController("/bill-setting.html").setViewName("bill-type");
            registry.addViewController("/forgetPassword.html").setViewName("forgetPassword");

        }
        /* 拦截器配置 ,注册拦截器*/
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new MyHandlerInterceptor()).addPathPatterns("/**")
                    .excludePathPatterns("/login","/login.html","/toRegister","/tologin","/static/**",
                            "/forgetPassword.html","/sendCode","/updatePassword","/toSignUp");

        }
//    /* 视图解析器 */
//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//
//    }

        //设置虚拟路径的映射
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            //无法直接访问 swagger-ui.html，需要做映射
            registry.addResourceHandler("swagger-ui.html")
                    .addResourceLocations("classpath:/META-INF/resources/");

            registry.addResourceHandler("docs.html")
                    .addResourceLocations("classpath:/META-INF/resources/");

            registry.addResourceHandler("/static/upload/imgs/**")
                    .addResourceLocations("http://123.207.252.249/src/main/resources/static/upload/imgs/");
        }

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

