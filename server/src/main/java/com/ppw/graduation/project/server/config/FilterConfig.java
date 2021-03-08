package com.ppw.graduation.project.server.config;

import com.ppw.graduation.project.server.listener.FilterListener;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version 1.0
 * @autohr 白日依山尽
 * @date 2021/2/25 23:33
 */

//过滤器配置——先创建过滤器
@Configuration
public class FilterConfig {

    //TODO：注册Filter
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean regFilter = new FilterRegistrationBean();
        //创建并注册AccessRecorderFilter
        regFilter.setFilter(new FilterListener());
        //设置拦截范围，/*拦截所有消息；
        regFilter.addUrlPatterns("/*");
        regFilter.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,*.map");
        //过滤器名字
        regFilter.setName("AccessRecorder");
        //如果系统中有多个过滤器，order决定哪个过滤器先执行(越小优先越高)
        regFilter.setOrder(1);
        return regFilter;
    }
}




















