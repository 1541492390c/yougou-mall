package per.ccm.ygmall.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.database.entity.BaseEntity;

/**
 * 订单项
 * */
@Getter
@Setter
@ToString
@TableName("order_item")
public class OrderItem extends BaseEntity {

    /**
     * 主键ID
     * */
    @TableId(type = IdType.ASSIGN_ID)
    private Long orderItemId;

    /**
     * 订单ID
     * */
    private Long orderId;

    /**
     * 商品ID
     * */
    private Long productId;

    /**
     * SkuID
     * */
    private Long skuId;

    /**
     * 数量
     * */
    private Integer quantity;

    /**
     * 总金额
     * */
    private Double totalAmount;

    /**
     * 商品名称
     * */
    private String productName;

    /**
     * 商品图片
     * */
    private String img;

    /**
     * 商品规格
     * */
    private String specs;

    /**
     * 是否评价
     * */
    private Boolean isComment;
}
