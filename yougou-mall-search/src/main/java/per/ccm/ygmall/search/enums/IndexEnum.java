package per.ccm.ygmall.search.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IndexEnum {

    /**
     * 商品
     * */
    PRODUCT_INDEX("yougou_mall_product"),

    /**
     * 分类
     * */
    CATEGORY_INDEX("yougou_mall_category"),

    /**
     * 品牌
     * */
    BRAND_INDEX("yougou_mall_brand");

    private final String value;
}
