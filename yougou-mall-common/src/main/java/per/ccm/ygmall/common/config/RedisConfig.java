package per.ccm.ygmall.common.config;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.config.Config;
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
import org.springframework.data.redis.serializer.*;
import per.ccm.ygmall.common.cache.Cache;

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

    private static final String R_LOCK_KEY = "9FD9A289-6FA0-CF19-1154-11EFFE7317D5";

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();

        configuration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

        Set<String> cacheNames = new HashSet<>();
        Map<String, RedisCacheConfiguration> configurationMap = new HashMap<>();
        for (Cache cache : Cache.values()) {
            cacheNames.add(cache.value());
            configurationMap.put(cache.value(), configuration.entryTtl(Duration.ofSeconds(cache.expired())));
        }
        return RedisCacheManager
                .builder(factory)
                .initialCacheNames(cacheNames)
                .withInitialCacheConfigurations(configurationMap)
                .build();
    }

    @Bean
    public RLock rLock() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + host + ":" + port).setPassword(password);
        return Redisson.create(config).getLock(R_LOCK_KEY);
    }
}
