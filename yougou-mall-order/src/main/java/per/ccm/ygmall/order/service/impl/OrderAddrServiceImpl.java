package per.ccm.ygmall.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.ccm.ygmall.common.util.ConvertUtils;
import per.ccm.ygmall.order.dto.OrderAddrDTO;
import per.ccm.ygmall.order.entity.OrderAddr;
import per.ccm.ygmall.order.mapper.OrderAddrMapper;
import per.ccm.ygmall.order.service.OrderAddrService;

@Service
public class OrderAddrServiceImpl implements OrderAddrService {

    @Autowired
    private OrderAddrMapper orderAddrMapper;

    @Override
    public void save(OrderAddrDTO orderAddrDTO) {
        OrderAddr orderAddr = ConvertUtils.convertProperties(orderAddrDTO, OrderAddr.class);
        orderAddrMapper.insert(orderAddr);
    }
}
