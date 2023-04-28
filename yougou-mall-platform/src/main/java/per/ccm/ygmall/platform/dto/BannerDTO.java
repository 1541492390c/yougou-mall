package per.ccm.ygmall.platform.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import per.ccm.ygmall.common.dto.BaseDTO;

/**
 * 轮播图传输数据
 * */
public class BannerDTO extends BaseDTO {

    @Schema(description = "主键ID")
    private Long bannerId;

    @Schema(description = "简介")
    private String desc;

    @Schema(description = "轮播图类型 1-PC端 2-移动端 3-小程序端")
    private Integer type;

    @Schema(description = "轮播图链接")
    private String link;

    @Schema(description = "图片地址")
    private String img;

    @Schema(description = "所属页面")
    private String page;

    @Schema(description = "是否启用")
    private Boolean enabled;

    public Long getBannerId() {
        return bannerId;
    }

    public void setBannerId(Long bannerId) {
        this.bannerId = bannerId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "BannerDTO{" +
                "bannerId=" + bannerId +
                ", desc='" + desc + '\'' +
                ", type=" + type +
                ", link='" + link + '\'' +
                ", img='" + img + '\'' +
                ", page='" + page + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
