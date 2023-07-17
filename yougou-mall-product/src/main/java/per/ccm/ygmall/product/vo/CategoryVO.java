package per.ccm.ygmall.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.vo.BaseVO;

import java.util.List;

/**
 * 分类信息
 * */
@Getter
@Setter
@ToString
public class CategoryVO extends BaseVO {

    @Schema(description = "主键ID")
    private Long categoryId;

    @Schema(description = "父级分类ID")
    private Long parentId;

    @Schema(description = "分类级别 1-一级分类 2-二级分类")
    private Integer level;

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "分类节点")
    private String node;

    @Schema(description = "子分类")
    private List<CategoryVO> children;
}
