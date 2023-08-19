package per.ccm.ygmall.api.product.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class SkuBO {

    /**
     * skuID
     * */
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
     * 单价
     * */
    private BigDecimal price;

    /**
     * 折扣价格
     * */
    private BigDecimal discountPrice;

    /**
     * sku规格
     * */
    private String specs;
}
