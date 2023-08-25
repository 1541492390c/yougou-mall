package per.ccm.ygmall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.database.entity.BaseEntity;

/**
 * 商品收藏
 * */
@Getter
@Setter
@ToString
@TableName("favorite")
public class Favorite extends BaseEntity {

    /**
     * 主键ID
     * */
    @TableId(type = IdType.AUTO)
    private Long favoriteId;

    /**
     * 商品ID
     * */
    private Long productId;

    /**
     * 用户ID
     * */
    private Long userId;
}
