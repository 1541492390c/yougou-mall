package per.ccm.ygmall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.database.entity.BaseEntity;

/**
 * 商品属性
 * */
@Getter
@Setter
@ToString
@TableName("attr")
public class Attr extends BaseEntity {

    /**
     * 主键ID
     * */
    @TableId(type = IdType.AUTO)
    private Long attrId;

    /**
     * 商品ID
     * */
    private Long productId;

    /**
     * 属性名称
     * */
    private String name;
}
