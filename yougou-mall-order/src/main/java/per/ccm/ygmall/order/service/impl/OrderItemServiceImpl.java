package per.ccm.ygmall.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.ccm.ygmall.common.util.ConvertUtils;
import per.ccm.ygmall.order.dto.OrderItemDTO;
import per.ccm.ygmall.order.entity.OrderItem;
import per.ccm.ygmall.order.mapper.OrderItemMapper;
import per.ccm.ygmall.order.service.OrderItemService;

import java.util.List;

@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public void batchSave(List<OrderItemDTO> orderItemDTOList) {
        List<OrderItem> orderItemList = ConvertUtils.converList(orderItemDTOList, OrderItem.class);
        orderItemList.forEach(item -> orderItemMapper.insert(item));
    }
}
