package com.source.plusutil.config

import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.ehcache.EhCacheCacheManager
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource


@Configuration
@EnableCaching
class CacheConfig {


    @Bean
    fun ehCacheManagerFactoryBean(): EhCacheManagerFactoryBean? {
        val ehCacheManagerFactoryBean = EhCacheManagerFactoryBean()
        ehCacheManagerFactoryBean.setConfigLocation(
                ClassPathResource("ehcache.xml"))
        ehCacheManagerFactoryBean.setShared(true)
        return ehCacheManagerFactoryBean
    }

    @Bean
    fun ehCacheCacheManager(ehCacheManagerFactoryBean: EhCacheManagerFactoryBean): EhCacheCacheManager? {
        val ehCacheCacheManager = EhCacheCacheManager()
        ehCacheCacheManager.cacheManager = ehCacheManagerFactoryBean.getObject()
        return ehCacheCacheManager
    }
}