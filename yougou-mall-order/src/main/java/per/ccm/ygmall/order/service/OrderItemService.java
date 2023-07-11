package per.ccm.ygmall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import per.ccm.ygmall.order.dto.OrderItemDTO;
import per.ccm.ygmall.order.entity.OrderItem;

import java.util.List;

public interface OrderItemService extends IService<OrderItem> {
    /**
     * 批量保存订单项
     *
     * @param orderItemDTOList 订单项传输数据列表
     * */
    void batchSave(List<OrderItemDTO> orderItemDTOList) throws Exception;
}
