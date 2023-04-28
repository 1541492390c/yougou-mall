package per.ccm.ygmall.product.dto;

import per.ccm.ygmall.common.dto.BaseDTO;

/**
 * 分类传输数据
 * */
public class CategoryDTO extends BaseDTO {

    /**
     * 主键ID
     * */
    private Long categoryId;

    /**
     * 上级分类ID
     * */
    private Long parentId;

    /**
     * 分类级别
     * */
    private Integer level;

    /**
     * 分类名称
     * */
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
        return "CategoryDTO{" +
                "categoryId=" + categoryId +
                ", parentId=" + parentId +
                ", level=" + level +
                ", name='" + name + '\'' +
                '}';
    }
}
