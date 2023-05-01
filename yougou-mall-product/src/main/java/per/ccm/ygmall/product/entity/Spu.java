package per.ccm.ygmall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import per.ccm.ygmall.common.entity.BaseEntity;

/**
 * 商品Spu
 * */
@TableName("spu")
public class Spu extends BaseEntity {

    /**
     * 主键ID
     * */
    @TableId(type = IdType.ASSIGN_ID)
    private Long spuId;

    /**
     * 品牌ID
     * */
    @TableField("brand_id")
    private Long brandId;

    /**
     * 商品状态 0-上架 1-已下架
     * */
    @TableField("state")
    private Integer state;

    /**
     * 分类列表
     * */
    @TableField("categories")
    private String categories;

    /**
     * 商品名称
     * */
    @TableField("name")
    private String name;

    /**
     * 商品封面
     * */
    @TableField("cover")
    private String cover;

    /**
     * 商品列表
     * */
    @TableField("img_list")
    private String imgList;

    @TableField("recommended")
    private Boolean recommended;

    @TableField("enabled")
    private Boolean enabled;

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

    public Boolean getRecommended() {
        return recommended;
    }

    public void setRecommended(Boolean recommended) {
        this.recommended = recommended;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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
                ", recommended=" + recommended +
                ", enabled=" + enabled +
                '}';
    }
}
