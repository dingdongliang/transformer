package com.dyenigma.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Description:Redis集群session共享配置
 * author  dyenigma
 * date 2017/10/18 9:45
 */
@Configuration
@EnableRedisHttpSession
public class RedisClusterConfig {

}
