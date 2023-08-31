package per.ccm.ygmall.search.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductSearchTypeEnum {

    /**
     * 全量搜索(查询品牌、分类、商品)
     * */
    ALL(1, "all"),

    /**
     * 仅搜索商品
     * */
    PRODUCT(2, "product");

    private final Integer value;

    private final String message;
}
