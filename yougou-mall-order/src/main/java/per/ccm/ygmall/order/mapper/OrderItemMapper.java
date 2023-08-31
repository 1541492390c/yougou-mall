package per.ccm.ygmall.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import per.ccm.ygmall.order.entity.OrderItem;
import per.ccm.ygmall.order.vo.OrderItemVO;

import java.util.List;

@Repository
public interface OrderItemMapper extends BaseMapper<OrderItem> {
    List<OrderItemVO> selectOrderItemListByOrderId(Long orderId) throws Exception;
}
