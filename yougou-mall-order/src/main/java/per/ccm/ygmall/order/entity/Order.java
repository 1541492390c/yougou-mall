package per.ccm.ygmall.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.database.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单
 * */
@Getter
@Setter
@ToString
@TableName("`order`")
public class Order extends BaseEntity {

    /**
     * 主键ID
     * */
    @TableId(type = IdType.ASSIGN_ID)
    private Long orderId;

    /**
     * 用户ID
     * */
    private Long userId;

    /**
     * 用户优惠券ID
     * */
    private Long couponUserId;

    /**
     * 订单状态 0-已取消 1-待付款 2-待发货 3-配送中 4-已完成
     * */
    private Integer state;

    /**
     * 订单总额
     * */
    private BigDecimal totalAmount;

    /**
     * 实付金额
     * */
    private BigDecimal payAmount;

    /**
     * 是否支付
     * */
    private Boolean isPay;

    /**
     * 是否启用
     * */
    private Boolean enabled;

    /**
     * 订单号
     * */
    private String orderNo;

    /**
     * 备注
     * */
    private String remark;

    /**
     * 下单时间
     * */
    private String submitTime;

    /**
     * 取消时间
     * */
    private Date cancelTime;

    /**
     * 支付时间
     * */
    private Date payTime;

    /**
     * 发货时间
     * */
    private Date deliveryTime;

    /**
     * 订单完成时间
     * */
    private Date finishTime;
}
