package com.example.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@SpringBootApplication
public class RedisApplication  extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RedisApplication.class);
    }

    @Autowired
    private RedissonProperties redissonProperties;

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setDefaultSerializer(stringRedisSerializer);
        template.setKeySerializer(stringRedisSerializer);
        template.setValueSerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setHashValueSerializer(stringRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    RedissonClient redissonClient() {
        Config config = new Config();
        //指定编码，默认编码为org.redisson.codec.JsonJacksonCodec
        //之前使用的spring-data-redis，用的客户端jedis，编码为org.springframework.data.redis.serializer.StringRedisSerializer
        //改用redisson后为了之间数据能兼容，这里修改编码为org.redisson.client.codec.StringCodec
        ClusterServersConfig clusterServersConfig = config.useClusterServers();
        clusterServersConfig.addNodeAddress(redissonProperties.getSentinelAddresses());
        clusterServersConfig.setTimeout(5000).setScanInterval(1000) //设置集群状态扫描时间
                .setMasterConnectionPoolSize(10000) //设置连接数
                .setPingConnectionInterval(60000)
                .setSlaveConnectionPoolSize(10000);
        if (!"".equalsIgnoreCase(redissonProperties.getPassword()))
            clusterServersConfig.setPassword(redissonProperties.getPassword());
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }


    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }

}
