package com.dyenigma.config;

import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: Druid监控spring jdbc配置
 * author  dyenigma
 * date 2017/7/26 9:02
 */
@Configuration
public class DruidConfigurer {
    /**
     * 定义监听Spring拦截器
     * return
     */
    @Bean
    public DruidStatInterceptor druidStatInterceptor() {
        return new DruidStatInterceptor();
    }

    /**
     * 定义监听Spring切入点
     * return
     */
    @Bean
    public JdkRegexpMethodPointcut druidStatPointcut() {
        JdkRegexpMethodPointcut druidStatPointcut = new JdkRegexpMethodPointcut();
        String patterns = "com.dyenigma.*.*.service.*";
        String patterns2 = "com.dyenigma.*.*.dao.*";
        druidStatPointcut.setPatterns(patterns, patterns2);
        return druidStatPointcut;
    }

    /**
     * 定义监听Spring通知类
     * return
     */
    @Bean
    public Advisor druidStatAdvisor() {
        return new DefaultPointcutAdvisor(druidStatPointcut(), druidStatInterceptor());
    }
}
