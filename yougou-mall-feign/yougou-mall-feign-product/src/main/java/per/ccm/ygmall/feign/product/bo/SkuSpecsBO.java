package per.ccm.ygmall.feign.product.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SkuSpecsBO {

    /**
     * 属性名称
     * */
    private String attrName;

    /**
     * 属性值
     * */
    private String attrValueName;
}
