package per.ccm.ygmall.search.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.search.vo.base.ESBaseVO;

@Setter
@Getter
@ToString
public class ESCategoryVO extends ESBaseVO {

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

    @Schema(description = "搜索类型")
    private String type;
}
