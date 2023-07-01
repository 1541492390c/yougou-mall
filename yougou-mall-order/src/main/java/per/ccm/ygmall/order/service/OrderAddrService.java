package per.ccm.ygmall.order.service;

import per.ccm.ygmall.order.dto.OrderAddrDTO;

public interface OrderAddrService {
    /**
     * 保存订单收货地址
     *
     * @param orderAddrDTO 订单收货地址信息
     * */
    void save(OrderAddrDTO orderAddrDTO);
}
