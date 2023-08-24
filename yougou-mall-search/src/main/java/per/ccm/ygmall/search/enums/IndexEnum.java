package per.ccm.ygmall.search.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IndexEnum {

    PRODUCT_INDEX("yougou_mall_product"),

    CATEGORY_INDEX("yougou_mall_category"),

    BRAND_INDEX("yougou_mall_brand");

    private final String value;
}
