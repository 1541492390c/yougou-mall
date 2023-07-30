package per.ccm.ygmall.api.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.api.order.bo.OrderBO;
import per.ccm.ygmall.api.order.bo.OrderItemBO;
import per.ccm.ygmall.common.config.FeignUrlConfig;
import per.ccm.ygmall.common.response.ResponseEntity;

@FeignClient("yougou-mall-order")
@RequestMapping(FeignUrlConfig.FEIGN_INNER_URL + "/order")
public interface OrderFeign {
    /**
     * 根据订单ID获取订单内部传输数据
     *
     * @param orderId 订单ID
     * */
    @GetMapping("/get_by_id")
    ResponseEntity<OrderBO> getOrderBOById(@RequestParam("order_id") Long orderId) throws Exception;

    /**
     * 更新订单状态
     * */
    @PutMapping("/pay_success")
    ResponseEntity<Void> paySuccess(@RequestParam("order_no") String orderNO) throws Exception;

    /**
     * 更新订单项
     *
     * @param orderItemBO 订单项内部传输数据
     * */
    @PutMapping("/order_item/update")
    ResponseEntity<Void> updateOrderItem(@RequestBody OrderItemBO orderItemBO) throws Exception;
}
