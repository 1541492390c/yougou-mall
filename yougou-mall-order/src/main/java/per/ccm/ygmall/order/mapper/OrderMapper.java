package per.ccm.ygmall.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import per.ccm.ygmall.order.entity.Order;
import per.ccm.ygmall.order.vo.OrderVO;

@Repository
public interface OrderMapper extends BaseMapper<Order> {
    OrderVO selectOrderById(Long orderId);

    Page<OrderVO> selectOrderPages(@Param("userId") Long userId, Page<Order> page);

    Page<OrderVO> searchOrderPages(@Param("state") Integer state, @Param("orderNo") String orderNo, Page<Order> page);
}
