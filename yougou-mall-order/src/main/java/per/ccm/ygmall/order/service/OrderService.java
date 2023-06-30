package per.ccm.ygmall.order.service;

import per.ccm.ygmall.order.dto.OrderDTO;

public interface OrderService {
    /**
     * 保存订单信息
     *
     * @param orderDTO 订单传输数据
     * */
    void save(OrderDTO orderDTO) throws Exception;
}
