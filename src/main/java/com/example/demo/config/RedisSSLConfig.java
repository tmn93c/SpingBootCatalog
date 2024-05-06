package com.example.demo.config;

import java.io.IOException;

import com.example.demo.anotation.SkipClass;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.SslOptions;
import io.lettuce.core.protocol.ProtocolVersion;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@SkipClass
public class RedisSSLConfig {

    @Value("${CACHE_REDIS_HOST}")
    private String host;

    @Value("${CACHE_REDIS_PORT}")
    private int port;

    @Value("${CACHE_REDIS_PASSWORD}")
    private String password;

    @Value("${REDIS_TLS_ENABLED}")
    private boolean sslEnabled;

    @Value("${REDIS_CA_CERT}")
    private String certFileLocation;

    private final ResourceLoader resourceLoader;

    @Bean
    RedisConnectionFactory redisConnectionFactory() throws IOException {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        redisStandaloneConfiguration.setPassword(password);

        LettuceClientConfiguration.LettuceClientConfigurationBuilder lettuceClientConfigurationBuilder =
                LettuceClientConfiguration.builder();

        if (sslEnabled){
            SslOptions sslOptions = SslOptions.builder()
                    .trustManager(resourceLoader.getResource("file:" + certFileLocation).getFile())
                    .build();

            ClientOptions clientOptions = ClientOptions
                    .builder()
                    .sslOptions(sslOptions)
                    .protocolVersion(ProtocolVersion.RESP3)
                    .build();

            lettuceClientConfigurationBuilder
                    .clientOptions(clientOptions)
                    .useSsl().disablePeerVerification();
        }

        LettuceClientConfiguration lettuceClientConfiguration = lettuceClientConfigurationBuilder.build();

        return new LettuceConnectionFactory(redisStandaloneConfiguration, lettuceClientConfiguration);
    }

}