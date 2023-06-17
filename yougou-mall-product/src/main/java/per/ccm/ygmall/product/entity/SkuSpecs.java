package per.ccm.ygmall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.database.entity.BaseEntity;

/**
 * sku和属性值关联
 * */
@Getter
@Setter
@ToString
@TableName("sku_specs")
public class SkuSpecs extends BaseEntity {

    /**
     * 主键ID
     * */
    @TableId(type = IdType.AUTO)
    private Long skuSpecsId;

    /**
     * 商品ID
     * */
    private Long productId;

    /**
     * skuID
     * */
    private Long skuId;

    /**
     * 属性ID
     * */
    private Long attrId;

    /**
     * 属性值ID
     * */
    private Long attrValueId;

    /**
     * 属性名称
     * */
    private String attrName;

    /**
     * 属性值名称
     * */
    private String attrValueName;
}
