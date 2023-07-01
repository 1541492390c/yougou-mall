package per.ccm.ygmall.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.database.entity.BaseEntity;

import java.util.Date;

/**
 * 订单
 * */
@Getter
@Setter
@ToString
@TableName("order")
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
     * 订单状态 0-已取消 1-待付款 2-待发货 3-配送中 4-已完成
     * */
    private Integer state;

    /**
     * 订单总额
     * */
    private Double totalAmount;

    /**
     * 是否支付
     * */
    private Boolean isPay;

    /**
     * 是否启用
     * */
    @TableLogic(value = "1", delval = "0")
    private Boolean enabled;

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
