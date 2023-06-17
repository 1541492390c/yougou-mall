package per.ccm.ygmall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import per.ccm.ygmall.database.entity.BaseEntity;

/**
 * 商品收藏
 * */
@Getter
@Setter
@TableName("collection")
public class Collection extends BaseEntity {

    /**
     * 主键ID
     * */
    @TableId(type = IdType.AUTO)
    private Long collectionId;

    /**
     * 商品ID
     * */
    private Long productId;

    /**
     * 用户ID
     * */
    private Long userId;

    @Override
    public String toString() {
        return "Collection{" +
                "collectionId=" + collectionId +
                ", productId=" + productId +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
