package per.ccm.ygmall.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.database.entity.BaseEntity;

/**
 * 订单收获地址
 * */
@Getter
@Setter
@ToString
@TableName("order_addr")
public class OrderAddr extends BaseEntity {

    /**
     * 主键ID
     * */
    @TableId(type = IdType.AUTO)
    private Long orderAddrId;

    /**
     * 订单ID
     * */
    private Long orderId;

    /**
     * 用户ID
     * */
    private Long userId;

    /**
     * 收获地址ID
     * */
    private Long addrId;

    /**
     * 收货人
     * */
    private String consignee;

    /**
     * 联系电话
     * */
    private String telephone;

    /**
     * 省份
     * */
    private String province;

    /**
     * 城市
     * */
    private String city;

    /**
     * 区、县
     * */
    private String district;

    /**
     * 详细地址
     * */
    private String detailedAddr;
}
