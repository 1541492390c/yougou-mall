package per.ccm.ygmall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.database.entity.BaseEntity;

/**
 * 商品分类
 * */
@Getter
@Setter
@ToString
@TableName("category")
public class Category extends BaseEntity {

    /**
     * 主键ID
     * */
    @TableId(type = IdType.AUTO)
    private Long categoryId;

    /**
     * 父级分类ID 顶级分类为0
     * */
    private Long parentId;

    /**
     * 分类级别
     * */
    private Integer level;

    /**
     * 分类名称
     * */
    private String name;
}
