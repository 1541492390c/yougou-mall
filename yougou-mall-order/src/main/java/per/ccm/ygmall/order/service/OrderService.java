package per.ccm.ygmall.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import per.ccm.ygmall.feign.order.bo.OrderBO;
import per.ccm.ygmall.common.basic.vo.PageVO;
import per.ccm.ygmall.order.dto.OrderDTO;
import per.ccm.ygmall.order.entity.Order;
import per.ccm.ygmall.order.vo.OrderVO;

public interface OrderService extends IService<Order> {
    /**
     * 保存订单信息,返回订单号
     *
     * @param orderDTO 订单传输数据
     * */
    Long save(OrderDTO orderDTO) throws Exception;

    /**
     * 根据主键ID获取订单内部传输数据
     *
     * @param orderId 主键ID
     * @return 订单内部传输数据
     * */
    OrderBO getOrderBOById(Long orderId) throws Exception;

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
     * 获取订单分页信息
     *
     * @param state 订单状态 0-已取消 1-待付款 2-待发货 3-配送中 4-已完成
     * @param orderNo 订单号
     * @param page 分页信息
     * @return 订单分页信息
     * */
    PageVO<OrderVO> getOrderPages(Integer state, String orderNo, Page<Order> page) throws Exception;

    /**
     * 更新订单
     *
     * @param orderDTO 订单传输数据
     * */
    void update(OrderDTO orderDTO) throws Exception;
}
