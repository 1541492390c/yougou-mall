package per.ccm.ygmall.order.service;

import per.ccm.ygmall.order.dto.OrderDTO;
import per.ccm.ygmall.order.vo.OrderVO;

public interface OrderService {
    /**
     * 保存订单信息
     *
     * @param orderDTO 订单传输数据
     * */
    void save(OrderDTO orderDTO) throws Exception;

    /**
     * 根据主键ID获取订单信息
     *
     * @param orderId 主键ID
     * @return 订单信息
     * */
    OrderVO getOrderById(Long orderId) throws Exception;

    /**
     * 更新订单状态
     * */
    void update(OrderDTO orderDTO) throws Exception;
}
