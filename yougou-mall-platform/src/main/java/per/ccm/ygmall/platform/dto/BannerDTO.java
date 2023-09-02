package per.ccm.ygmall.platform.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 轮播图传输数据
 * */
@Getter
@Setter
@ToString
public class BannerDTO {

    @Schema(description = "主键ID")
    private Long bannerId;

    @Schema(description = "轮播图类型 1-PC端 2-移动端 3-小程序端")
    private Integer type;

    @Schema(description = "简介")
    private String description;

    @Schema(description = "轮播图链接")
    private String link;

    @Schema(description = "图片地址")
    private String img;

    @Schema(description = "所属页面")
    private String page;
}
