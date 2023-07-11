package per.ccm.ygmall.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import per.ccm.ygmall.database.vo.PageVO;
import per.ccm.ygmall.order.dto.OrderDTO;
import per.ccm.ygmall.order.entity.Order;
import per.ccm.ygmall.order.vo.OrderVO;

public interface OrderService extends IService<Order> {
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
     * 获取订单分页信息
     *
     * @param userId 用户ID
     * @param page 分页信息
     * @return 订单分页信息
     * */
    PageVO<OrderVO> getOrderPages(Long userId, Page<Order> page) throws Exception;

    /**
     * 更新订单状态
     * */
    void update(OrderDTO orderDTO) throws Exception;
}
