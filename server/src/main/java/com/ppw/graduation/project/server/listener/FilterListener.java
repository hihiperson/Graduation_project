package com.ppw.graduation.project.server.listener;

import com.ppw.graduation.project.server.enums.Dynamic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/**
 * @version 1.0
 * @autohr 白日依山尽
 * @date 2021/2/25 23:39
 */

//创建过滤器——配置要实现的功能
public class FilterListener implements Filter {

    private final static Logger log = LoggerFactory.getLogger(FilterListener.class);  //定义日志输出器

    @Autowired
    private Dynamic dynamic;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //filterChain过滤器链，会把请求向下传输
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String uri = request.getRequestURI();
        //String ua = request.getHeader("user-agent");    //可以知道是pc还是手机
        String ip = request.getRemoteAddr();    //获取ip，大多时间靠谱，可能是代理地点

        dynamic.getIp(ip);

        Long st = new Date().getTime();

        filterChain.doFilter(servletRequest, servletResponse);

        Long et = new Date().getTime();     //得到程序运行时间
        dynamic.getUseTime(et-st);
        //打印看看
        log.info("uri:{}, ip来源:{}, 耗时time:{}", uri, ip, (et-st));
    }

    @Override
    public void destroy() {

    }
}





