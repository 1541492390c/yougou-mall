package per.ccm.ygmall.payment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.database.entity.BaseEntity;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@TableName("payment_log")
public class PaymentLog extends BaseEntity {

    /**
     * 主键ID
     * */
    @TableId(type = IdType.ASSIGN_ID)
    private Long paymentLogId;

    /**
     * 支付总额
     * */
    private BigDecimal totalAmount;

    /**
     * 支付状态 0-支付失败 1-支付成功
     * */
    private Integer state;

    /**
     * 订单号
     * */
    private String orderNo;

    /**
     * 信息
     * */
    private String message;
}
