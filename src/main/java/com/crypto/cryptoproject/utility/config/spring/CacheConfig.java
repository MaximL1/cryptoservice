package com.crypto.cryptoproject.utility.config.spring;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    @RequestScope
    public CacheManager requestScopedCacheManager() {
        return new ConcurrentMapCacheManager();
    }
}
