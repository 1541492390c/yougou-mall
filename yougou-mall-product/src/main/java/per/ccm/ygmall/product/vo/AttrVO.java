package per.ccm.ygmall.product.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.basic.vo.BaseVO;

import java.util.List;

/**
 * 商品属性信息
 * */
@Getter
@Setter
@ToString
public class AttrVO extends BaseVO {

    @Schema(description = "主键ID")
    private Long attrId;

    @Schema(description = "商品ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long productId;

    @Schema(description = "属性名称")
    private String name;

    @Schema(description = "属性值列表")
    private List<AttrValueVO> attrValueList;
}
