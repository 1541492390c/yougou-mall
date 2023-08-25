package per.ccm.ygmall.platform.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.basic.vo.BaseVO;

/**
 * 轮播图信息
 * */
@Getter
@Setter
@ToString
public class BannerVO extends BaseVO {

    @Schema(description = "主键ID")
    private Long bannerId;

    @Schema(description = "所属页面")
    private String page;

    @Schema(description = "简介")
    private String description;

    @Schema(description = "轮播图链接")
    private String link;

    @Schema(description = "图片地址")
    private String img;
}
