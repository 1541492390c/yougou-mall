package per.ccm.ygmall.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 分类传输数据
 * */
@Getter
@Setter
@ToString
public class CategoryDTO {

    @Schema(description = "主键ID")
    private Long categoryId;

    @Schema(description = "上级分类ID")
    private Long parentId;

    @Schema(description = "分类级别")
    private Integer level;

    @Schema(description = "分类名称")
    private String name;
}
