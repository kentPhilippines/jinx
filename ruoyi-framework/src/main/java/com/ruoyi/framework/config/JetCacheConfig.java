package com.ruoyi.framework.config;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCreateCacheAnnotation
@EnableMethodCache(basePackages = "com.ruoyi")
public class JetCacheConfig {

}
