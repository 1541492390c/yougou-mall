package per.ccm.ygmall.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.ccm.ygmall.common.exception.YougouException;
import per.ccm.ygmall.common.response.ResponseCodeEnum;
import per.ccm.ygmall.common.util.ConvertUtils;
import per.ccm.ygmall.order.dto.OrderAddrDTO;
import per.ccm.ygmall.order.entity.OrderAddr;
import per.ccm.ygmall.order.mapper.OrderAddrMapper;
import per.ccm.ygmall.order.service.OrderAddrService;

@Service
public class OrderAddrServiceImpl extends ServiceImpl<OrderAddrMapper, OrderAddr> implements OrderAddrService {

    @Autowired
    private OrderAddrMapper orderAddrMapper;

    @Override
    public void save(OrderAddrDTO orderAddrDTO) {
        LambdaQueryWrapper<OrderAddr> queryWrapper = new LambdaQueryWrapper<>();
        // 订单只能对应一个订单收货地址
        if (orderAddrMapper.exists(queryWrapper.eq(OrderAddr::getOrderId, orderAddrDTO.getOrderId()))) {
            throw new YougouException(ResponseCodeEnum.ORDER_ERROR_C0001);
        }
        OrderAddr orderAddr = ConvertUtils.convertProperties(orderAddrDTO, OrderAddr.class);
        orderAddrMapper.insert(orderAddr);
    }
}
