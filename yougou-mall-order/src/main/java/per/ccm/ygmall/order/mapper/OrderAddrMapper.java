package per.ccm.ygmall.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import per.ccm.ygmall.order.entity.OrderAddr;
import per.ccm.ygmall.order.vo.OrderAddrVO;

@Repository
public interface OrderAddrMapper extends BaseMapper<OrderAddr> {
    OrderAddrVO selectOrderAddrByOrderId(Long orderId);
}
