package per.ccm.ygmall.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;
import per.ccm.ygmall.order.entity.Order;
import per.ccm.ygmall.order.vo.OrderVO;

@Repository
public interface OrderMapper extends BaseMapper<Order> {
    OrderVO selectOrderById(Long orderId);

    Page<OrderVO> selectOrderPages(Long userId, Page<Order> page);
}
