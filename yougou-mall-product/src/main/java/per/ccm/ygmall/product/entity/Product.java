package per.ccm.ygmall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import per.ccm.ygmall.database.entity.BaseEntity;

/**
 * 商品
 * */
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
     * 分类列表
     * */
    private String categories;

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
     * 是否推荐
     * */
    private Boolean recommended;

    /**
     * 是否启用
     * */
    @TableLogic(value = "1", delval = "0")
    private Boolean enabled;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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
        return "Product{" +
                "productId=" + productId +
                ", brandId=" + brandId +
                ", state=" + state +
                ", categories='" + categories + '\'' +
                ", name='" + name + '\'' +
                ", cover='" + cover + '\'' +
                ", imgList='" + imgList + '\'' +
                ", recommended=" + recommended +
                ", enabled=" + enabled +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
