package com.example.demo.config;

import com.example.demo.anotation.SkipClass;
import com.example.demo.constant.CacheConstant;
import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@SkipClass
public class CacheConfiguration {
    @ConditionalOnExpression("'${cache.mode}' == '" + CacheConstant.MODE_LOCAL + "'")
    public static class LocalCacheConfiguration {
        private final Logger log = LoggerFactory.getLogger(this.getClass());
        
        @Bean
        public CacheManager cacheManager() {
            return new CaffeineCacheManager();
        }
    }
    
    @ConditionalOnExpression("'${cache.mode}' == '" + CacheConstant.MODE_DISTRIBUTED + "'")
    public static class DistributedCacheConfiguration {
        private final Logger log = LoggerFactory.getLogger(this.getClass());

        @Value("${cache.multicast.ip}")
        private String multicastIP;

        @Value("${cache.multicast.port}")
        private int multicastPort;
        
        @Bean(name=CacheConstant.Distributed.HAZELCAST_BEAN_NAME)
        public HazelcastInstance hazelcastInstance(@Value("${cache.hazelcast.instance-name}") String hazelcastInstanceName) {
            Config config = new Config();
            config.setInstanceName(hazelcastInstanceName);
            
            NetworkConfig network = config.getNetworkConfig();
            network.setPort(0);

            JoinConfig join = network.getJoin();
            join.getTcpIpConfig().setEnabled(false);
            join.getAwsConfig().setEnabled(false);
            join.getMulticastConfig().setEnabled(true);

            join.getMulticastConfig().setMulticastGroup(multicastIP);
            join.getMulticastConfig().setMulticastPort(multicastPort);
            join.getMulticastConfig().setMulticastTimeoutSeconds(10);
            
            return Hazelcast.newHazelcastInstance(config);
        }
        
        @Bean
        public CacheManager cacheManager(HazelcastInstance hazelcastInstance) {
            return new HazelcastCacheManager(hazelcastInstance);
        }
    }
}
