package per.ccm.ygmall.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import per.ccm.ygmall.common.vo.BaseVO;

import java.util.List;

/**
 * 分类信息
 * */
public class CategoryVO extends BaseVO {

    @Schema(description = "主键ID")
    private Long categoryId;

    @Schema(description = "父级分类ID")
    private Long parentId;

    @Schema(description = "分类级别 1-一级分类 2-二级分类")
    private Integer level;

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "子分类")
    private List<CategoryVO> children;

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

    public List<CategoryVO> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryVO> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "CategoryVO{" +
                "categoryId=" + categoryId +
                "parentId=" + parentId +
                ", level=" + level +
                ", name='" + name + '\'' +
                ", children=" + children +
                '}';
    }
}
