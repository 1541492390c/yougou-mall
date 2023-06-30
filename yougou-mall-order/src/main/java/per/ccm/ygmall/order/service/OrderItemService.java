package per.ccm.ygmall.order.service;

import per.ccm.ygmall.order.dto.OrderItemDTO;

import java.util.List;

public interface OrderItemService {
    /**
     * 批量保存订单项
     *
     * @param orderItemDTOList 订单项传输数据列表
     * */
    void batchSave(List<OrderItemDTO> orderItemDTOList) throws Exception;
}
