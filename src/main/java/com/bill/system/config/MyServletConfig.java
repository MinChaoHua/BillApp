package com.bill.system.config;

import com.bill.system.filter.MyFilter;
import com.bill.system.listener.MyListener;
import com.bill.system.servlet.MyServlet;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.EventListener;

@Configuration
public class MyServletConfig {

    //2.0 以上版本 EmbeddedServletContainerCustomizer 被 WebServerFactoryCustomizer 替代

//    //配置嵌入式的Servlet容器,2.0一下版本
//    @Bean
//    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer(){
//        return new EmbeddedServletContainerCustomizer() {
//
//            //定制嵌入式的Servlet容器相关的规则
//            @Override
//            public void customize(ConfigurableEmbeddedServletContainer container) {
//                container.setPort(8083);
//            }
//        };
//    }

    //配置嵌入式的Servlet容器
    @Bean//加入容器
    public WebServerFactoryCustomizer embeddedServletContainerCustomizer(){
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory >(){
            //定制嵌入式的Servlet容器相关的规则

            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                factory.setPort(8080);
            }
        };
    }

    //注册三大组件
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        ServletRegistrationBean servletRegistrationBean =
                new ServletRegistrationBean(new MyServlet(),"/servlet");
        return  servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new MyFilter());
        filterFilterRegistrationBean.setUrlPatterns(Arrays.asList("/hello","/good"));
        return filterFilterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean(){
        ServletListenerRegistrationBean<EventListener> listenerRegistrationBean = new ServletListenerRegistrationBean<>();
        listenerRegistrationBean.setListener(new MyListener());
        return listenerRegistrationBean;

    }



}
