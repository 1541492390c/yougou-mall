package per.ccm.ygmall.feign.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.feign.order.bo.OrderBO;
import per.ccm.ygmall.common.basic.config.FeignUrlConfig;
import per.ccm.ygmall.common.basic.response.ResponseEntity;

@FeignClient(value = "yougou-mall-order", contextId = "order")
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
}
