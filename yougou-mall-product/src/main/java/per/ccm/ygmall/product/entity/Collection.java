package per.ccm.ygmall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import per.ccm.ygmall.common.entity.BaseEntity;

/**
 * 商品收藏
 * */
@TableName("collection")
public class Collection extends BaseEntity {

    /**
     * 主键ID
     * */
    @TableId(type = IdType.AUTO)
    private Long collectionId;

    /**
     * spuID
     * */
    @TableField("spu_id")
    private Long spuId;

    /**
     * 用户ID
     * */
    @TableField("user_id")
    private Long userId;

    public Long getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Long collectionId) {
        this.collectionId = collectionId;
    }

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "collectionId=" + collectionId +
                ", spuId=" + spuId +
                ", userId=" + userId +
                '}';
    }
}
