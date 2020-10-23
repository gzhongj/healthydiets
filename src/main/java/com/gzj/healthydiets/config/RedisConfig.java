package com.gzj.healthydiets.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        //为了方便开发，直接采用<String，Object>
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        //创建序列化器
        //json的序列化器
        Jackson2JsonRedisSerializer j2jrs = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        j2jrs.setObjectMapper(om);
        //String序列化器
        StringRedisSerializer srs = new StringRedisSerializer();

        //为key、value配置序列化器
        //key采用String的序列化器
        template.setKeySerializer(srs);
        //Hash的key也采用String的序列化器
        template.setHashKeySerializer(srs);
        //value采用json的序列化器
        template.setValueSerializer(j2jrs);
        //hash的value也采用json的序列化器
        template.setHashValueSerializer(j2jrs);
        return template;
    }
}
