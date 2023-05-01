package per.ccm.ygmall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import per.ccm.ygmall.common.entity.BaseEntity;

/**
 * 商品分类
 * */
@TableName("category")
public class Category extends BaseEntity {

    /**
     * 主键ID
     * */
    @TableId(type = IdType.AUTO)
    private Long categoryId;

    /**
     * 父级分类ID 顶级分类为0
     * */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 分类级别
     * */
    @TableField("level")
    private Integer level;

    /**
     * 分类名称
     * */
    @TableField("name")
    private String name;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", parentId=" + parentId +
                ", level=" + level +
                ", name='" + name + '\'' +
                '}';
    }
}
