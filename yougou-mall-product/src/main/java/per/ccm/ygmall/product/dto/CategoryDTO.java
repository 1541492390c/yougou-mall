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

    @Schema(name = "主键ID")
    private Long categoryId;

    @Schema(name = "上级分类ID")
    private Long parentId;

    @Schema(name = "分类级别")
    private Integer level;

    @Schema(name = "分类名称")
    private String name;
}
