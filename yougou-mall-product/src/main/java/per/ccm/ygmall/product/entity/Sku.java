package per.ccm.ygmall.product.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.database.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * 商品Sku
 * */
@Getter
@Setter
@ToString
@TableName("sku")
public class Sku extends BaseEntity {

    /**
     * 主键ID
     * */
    @TableId(type = IdType.AUTO)
    private Long skuId;

    /**
     * 商品ID
     * */
    private Long productId;

    /**
     * sku库存
     * */
    private Integer skuStock;

    /**
     * sku价格
     * */
    private BigDecimal price;

    /**
     * 折扣价格
     * */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private BigDecimal discountPrice;
}
