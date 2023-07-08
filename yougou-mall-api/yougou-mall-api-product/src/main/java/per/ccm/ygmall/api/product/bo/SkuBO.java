package per.ccm.ygmall.api.product.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

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
    private Double price;

    /**
     * 折扣价格
     * */
    private Double discountPrice;

    /**
     * 是否折扣
     * */
    private Boolean isDiscount;

    /**
     * sku规格内部传输数据列表
     * */
    private List<SkuSpecsBO> skuSpecsBOList;
}
