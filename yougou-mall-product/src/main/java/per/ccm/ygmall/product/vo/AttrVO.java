package per.ccm.ygmall.product.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.vo.BaseVO;

import java.util.List;

/**
 * 商品属性信息
 * */
@Getter
@Setter
@ToString
public class AttrVO extends BaseVO {

    /**
     * 商品属性ID
     * */
    private Long attrId;

    /**
     * 商品ID
     * */
    private Long productId;

    /**
     * 属性名称
     * */
    private String name;

    /**
     * 属性值列表
     * */
    private List<AttrValueVO> attrValueList;
}
