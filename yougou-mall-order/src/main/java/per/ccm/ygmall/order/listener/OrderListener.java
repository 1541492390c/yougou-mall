package per.ccm.ygmall.order.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import per.ccm.ygmall.rabbitmq.config.RabbitmqConfig;

@Component
public class OrderListener {

    /**
     * 订单15分钟自动过期
     * */
    @RabbitListener(queues = RabbitmqConfig.ORDER_DEAD_LETTER_QUEUE)
    public void cancelOrder(Long orderId) {
        System.out.println(orderId);
    }
}
