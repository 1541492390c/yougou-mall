package per.ccm.ygmall.order.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.vo.BaseVO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单信息
 * */
@Getter
@Setter
@ToString
public class OrderVO extends BaseVO {

    @Schema(description = "主键ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long orderId;

    @Schema(description = "用户ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    @Schema(description = "订单状态 0-已取消 1-待付款 2-待发货 3-配送中 4-已完成")
    private Integer state;

    @Schema(description = "总金额")
    private BigDecimal totalAmount;

    @Schema(description = "是否支付")
    private Boolean isPay;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "下单时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date submitTime;

    @Schema(description = "取消时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date cancelTime;

    @Schema(description = "支付时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date payTime;

    @Schema(description = "发货时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date deliveryTime;

    @Schema(description = "完成时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date finishTime;

    @Schema(description = "订单收货地址")
    private OrderAddrVO orderAddrVO;

    @Schema(description = "订单项列表")
    private List<OrderItemVO> orderItemList;
}
