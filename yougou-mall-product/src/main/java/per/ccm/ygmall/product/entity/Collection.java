package per.ccm.ygmall.product.entity;

import org.hibernate.annotations.DynamicInsert;
import per.ccm.ygmall.common.entity.BaseEntity;

import javax.persistence.*;

/**
 * 商品收藏
 * */
@Entity
@DynamicInsert
@Table(name = "collection")
public class Collection extends BaseEntity {

    /**
     * 主键ID
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collection_id")
    private Long collectionId;

    /**
     * spuID
     * */
    @Column(name = "spu_id")
    private Long spuId;

    /**
     * 用户ID
     * */
    @Column(name = "user_id")
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
