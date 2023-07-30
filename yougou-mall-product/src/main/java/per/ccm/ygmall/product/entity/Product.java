package per.ccm.ygmall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.database.entity.BaseEntity;

/**
 * 商品
 * */
@Getter
@Setter
@ToString
@TableName("product")
public class Product extends BaseEntity {

    /**
     * 主键ID
     * */
    @TableId(type = IdType.ASSIGN_ID)
    private Long productId;

    /**
     * 品牌ID
     * */
    private Long brandId;

    /**
     * 商品状态 0-已下架 1-上架
     * */
    private Integer state;

    /**
     * 折扣
     * */
    private Integer discount;

    /**
     * 分类节点
     * */
    private String categoryNode;

    /**
     * 商品名称
     * */
    private String name;

    /**
     * 商品封面
     * */
    private String cover;

    /**
     * 商品列表
     * */
    private String imgList;

    /**
     * 是否折扣
     * */
    private Boolean isDiscount;

    /**
     * 是否推荐
     * */
    private Boolean recommended;

    /**
     * 是否启用
     * */
    @TableLogic(value = "1", delval = "0")
    private Boolean enabled;
}
