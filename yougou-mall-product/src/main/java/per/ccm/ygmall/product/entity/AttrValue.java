package per.ccm.ygmall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.database.entity.BaseEntity;

/**
 * 商品属性值
 * */
@Getter
@Setter
@ToString
@TableName("attr_value")
public class AttrValue extends BaseEntity {

    /**
     * 主键ID
     * */
    @TableId(type = IdType.AUTO)
    private Long attrValueId;

    /**
     * 属性ID
     * */
    private Long attrId;

    /**
     * 属性名称
     * */
    private String name;
}
