package per.ccm.ygmall.feign.product.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ProductBO {

    /**
     * 主键ID
     * */
    private Long productId;

    /**
     * 商品名称
     *
     */
    private String name;

    /**
     * 商品封面
     * */
    private String cover;

    /**
     * sku内部传输数据列表
     * */
    private List<SkuBO> skuBOList;
}
