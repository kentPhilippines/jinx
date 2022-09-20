package com.ruoyi.framework.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;

import java.time.Duration;

public class CustomRedisCacheManager extends RedisCacheManager {
    /*
     * @description 提供默认构造器
     * @date 2020/9/28 9:22
     * @param
     * @param cacheWriter
     * @param defaultCacheConfiguration
     * @return
     **/
    public CustomRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
    }
    /*
     * @description 重写父类createRedisCache方法
     * @date 2020/9/28 9:22
     * @param
     * @param name @Cacheable中的value
     * @param cacheConfig
     * @return org.springframework.data.redis.cache.RedisCache
     **/
    @Override
    protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {
        //名称中存在#标记进行到期时间配置
        if (!name.isEmpty() && name.contains("#")) {
            String[] SPEL = name.split("#");
            if (StringUtils.isNumeric(SPEL[1])) {
                //配置缓存到期时间
                int cycle = Integer.parseInt(SPEL[1]);
                return super.createRedisCache(SPEL[0], cacheConfig.entryTtl(Duration.ofMinutes(cycle * 24 * 60)));
            }
        }
        return super.createRedisCache(name, cacheConfig);
    }
}