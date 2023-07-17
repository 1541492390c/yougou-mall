package per.ccm.ygmall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.database.entity.BaseEntity;

/**
 * 品牌
 * */
@Getter
@Setter
@ToString
@TableName("brand")
public class Brand extends BaseEntity {

    /**
     * 主键ID
     * */
    @TableId(type = IdType.AUTO)
    private Long brandId;

    /**
     * 品牌名称
     * */
    private String name;

    /**
     * 分类节点
     * */
    private String categoryNode;

    /**
     * 品牌(logo)图片
     * */
    private String img;

    /**
     * 品牌描述
     * */
    private String description;
}
