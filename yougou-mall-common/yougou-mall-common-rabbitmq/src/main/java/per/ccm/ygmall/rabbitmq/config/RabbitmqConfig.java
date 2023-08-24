package per.ccm.ygmall.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitmqConfig {

    /**
     * 普通直连交换机
     * */
    public static final String DIRECT_EXCHANGE = "direct.exchange";

    /**
     * 死信交换机
     * */
    public static final String DEAD_LETTER_EXCHANGE = "dead.letter.exchange";

    /**
     * 订单延时队列
     * */
    public static final String ORDER_DELAY_QUEUE = "per.ccm.ygmall.order.delay.queue";

    /**
     * 订单死信队列
     * */
    public static final String ORDER_DEAD_LETTER_QUEUE = "per.ccm.ygmall.order.dead.letter.queue";

    /**
     * 订单死信队列路由
     * */
    public static final String ORDER_DEAD_LETTER_ROUTING_KEY = "per.ccm.ygmall.order.dead.letter.routing.key";

    /**
     * 订单延时队列路由
     * */
    public static final String ORDER_DELAY_ROUTING_KEY = "per.ccm.ygmall.order.delay.routing.key";

    /**
     * 直连交换机
     * */
    @Bean(DIRECT_EXCHANGE)
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    /**
     * 死信交换机
     * */
    @Bean(DEAD_LETTER_EXCHANGE)
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(DEAD_LETTER_EXCHANGE);
    }

    /**
     * 订单死信队列
     * */
    @Bean(ORDER_DEAD_LETTER_QUEUE)
    public Queue orderDeadLetterQueue() {
        return new Queue(ORDER_DEAD_LETTER_QUEUE);
    }

    /**
     * 订单延时队列
     * */
    @Bean(ORDER_DELAY_QUEUE)
    public Queue orderDelayQueue() {
        Map<String, Object> params = new HashMap<>();
        params.put("x-message-ttl", 15 * 60 * 1000); // 订单过期时间15分钟
        params.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        params.put("x-dead-letter-routing-key", ORDER_DEAD_LETTER_ROUTING_KEY);
        return new Queue(ORDER_DELAY_QUEUE, true, false, false, params);
    }

    @Bean
    public Binding orderDeadLetterBindingDeadLetterExchange() {
        return BindingBuilder.bind(orderDeadLetterQueue()).to(deadLetterExchange()).with(ORDER_DEAD_LETTER_ROUTING_KEY);
    }

    @Bean
    public Binding orderDelayQueueBindingDelayExchange() {
        return BindingBuilder.bind(orderDelayQueue()).to(directExchange()).with(ORDER_DELAY_ROUTING_KEY);
    }
}
