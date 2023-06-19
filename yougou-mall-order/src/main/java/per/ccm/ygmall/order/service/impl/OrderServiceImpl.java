package per.ccm.ygmall.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.ccm.ygmall.order.mapper.OrderMapper;
import per.ccm.ygmall.order.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
}
