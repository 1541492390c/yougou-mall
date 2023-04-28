package per.ccm.ygmall.product.entity;

import org.hibernate.annotations.DynamicInsert;
import per.ccm.ygmall.common.entity.BaseEntity;

import javax.persistence.*;

/**
 * 商品分类
 * */
@Entity
@DynamicInsert
@Table(name = "category")
public class Category extends BaseEntity {

    /**
     * 主键ID
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    /**
     * 父级分类ID 顶级分类为0
     * */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 分类级别
     * */
    @Column(name = "level")
    private Integer level;

    /**
     * 分类名称
     * */
    @Column(name = "name")
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
