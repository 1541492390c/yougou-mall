package per.ccm.ygmall.feign.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import per.ccm.ygmall.common.basic.config.FeignUrlConfig;
import per.ccm.ygmall.common.basic.response.ResponseEntity;
import per.ccm.ygmall.feign.order.bo.OrderItemBO;

@FeignClient(value = "yougou-mall-order", contextId = "order-item")
@RequestMapping(FeignUrlConfig.FEIGN_INNER_URL + "/order/order_item")
public interface OrderItemFeign {
    /**
     * 更新订单项
     *
     * @param orderItemBO 订单项内部传输数据
     * */
    @PutMapping("/order_item/update")
    ResponseEntity<Void> updateOrderItem(@RequestBody OrderItemBO orderItemBO) throws Exception;
}
