package per.ccm.ygmall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import per.ccm.ygmall.database.entity.BaseEntity;

/**
 * 品牌
 * */
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
     * 分类
     * */
    private String categories;

    /**
     * 品牌(logo)图片
     * */
    private String img;

    /**
     * 品牌描述
     * */
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "brandId=" + brandId +
                ", name='" + name + '\'' +
                ", categories='" + categories + '\'' +
                ", img='" + img + '\'' +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
