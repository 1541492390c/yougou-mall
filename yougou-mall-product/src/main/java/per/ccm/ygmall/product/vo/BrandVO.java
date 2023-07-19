package per.ccm.ygmall.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.vo.BaseVO;

/**
 * 品牌信息
 * */
@Getter
@Setter
@ToString
public class BrandVO extends BaseVO {

    @Schema(description = "主键ID")
    private Long brandId;

    @Schema(description = "品牌名称")
    private String name;

    @Schema(description = "分类节点")
    private String categoryNode;

    @Schema(description = "品牌(logo)图片")
    private String img;

    @Schema(description = "品牌描述")
    private String description;
}
