package per.ccm.ygmall.cache.config;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.config.Config;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.cache.cache.CacheEnum;

import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Configuration
@EnableCaching
@EnableConfigurationProperties(CacheProperties.class)
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${r-lock-key}")
    private String rLockKey;

    private static Config config;

    private Config getConfig() {
        if (ObjectUtils.isEmpty(config)) {
            config = new Config();
            config.useSingleServer().setAddress("redis://" + host + ":" + port).setPassword(password);
        }
        return config;
    }

    @Bean
    public RLock rLock() {
        Config config = this.getConfig();
        return Redisson.create(config).getLock(rLockKey);
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        Config config = this.getConfig();
        return new RedissonConnectionFactory(config);
    }

    @Bean
    public CacheManager cacheManager() {
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        configuration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

        Set<String> cacheNames = new HashSet<>();
        Map<String, RedisCacheConfiguration> configurationMap = new HashMap<>();
        for (CacheEnum cacheEnum : CacheEnum.values()) {
            cacheNames.add(cacheEnum.value());
            configurationMap.put(cacheEnum.value(), configuration.entryTtl(Duration.ofSeconds(cacheEnum.expired())));
        }
        return RedisCacheManager
                .builder(redisConnectionFactory())
                .initialCacheNames(cacheNames)
                .withInitialCacheConfigurations(configurationMap)
                .build();
    }
}
