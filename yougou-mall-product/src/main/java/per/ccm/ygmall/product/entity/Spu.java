package per.ccm.ygmall.product.entity;

import org.hibernate.annotations.DynamicInsert;
import per.ccm.ygmall.common.entity.BaseEntity;

import javax.persistence.*;

/**
 * 商品Spu
 * */
@Entity
@DynamicInsert
@Table(name = "spu")
public class Spu extends BaseEntity {

    /**
     * 主键ID
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spu_id")
    private Long spuId;

    /**
     * 品牌ID
     * */
    @Column(name = "brand_id")
    private Long brandId;

    /**
     * 商品状态 0-上架 1-已下架
     * */
    @Column(name = "state")
    private Integer state;

    /**
     * 分类列表
     * */
    @Column(name = "categories")
    private String categories;

    /**
     * 商品名称
     * */
    @Column(name = "name")
    private String name;

    /**
     * 商品封面
     * */
    @Column(name = "cover")
    private String cover;

    /**
     * 商品列表
     * */
    @Column(name = "img_list")
    private String imgList;

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getImgList() {
        return imgList;
    }

    public void setImgList(String imgList) {
        this.imgList = imgList;
    }

    @Override
    public String toString() {
        return "Spu{" +
                "spuId=" + spuId +
                ", brandId=" + brandId +
                ", state=" + state +
                ", categories='" + categories + '\'' +
                ", name='" + name + '\'' +
                ", cover='" + cover + '\'' +
                ", imgList='" + imgList + '\'' +
                '}';
    }
}
