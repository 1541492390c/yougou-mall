package per.ccm.ygmall.product.entity;

import org.hibernate.annotations.DynamicInsert;
import per.ccm.ygmall.common.entity.BaseEntity;

import javax.persistence.*;

/**
 * 品牌
 * */
@Entity
@DynamicInsert
@Table(name = "brand")
public class Brand extends BaseEntity {

    /**
     * 主键ID
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Long brandId;

    /**
     * 品牌名称
     * */
    @Column(name = "name")
    private String name;

    /**
     * 分类
     * */
    @Column(name = "categories")
    private String categories;

    /**
     * 品牌(logo)图片
     * */
    @Column(name = "img")
    private String img;

    @Column(name = "desc")
    private String desc;

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "brandId=" + brandId +
                ", name='" + name + '\'' +
                ", categories='" + categories + '\'' +
                ", img='" + img + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
