package per.ccm.ygmall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import per.ccm.ygmall.order.dto.OrderAddrDTO;
import per.ccm.ygmall.order.entity.OrderAddr;

public interface OrderAddrService extends IService<OrderAddr> {
    /**
     * 保存订单收货地址
     *
     * @param orderAddrDTO 订单收货地址信息
     * */
    void save(OrderAddrDTO orderAddrDTO);
}
