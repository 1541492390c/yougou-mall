package per.ccm.ygmall.platform.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import per.ccm.ygmall.common.vo.BaseVO;

/**
 * 轮播图信息
 * */
public class BannerVO extends BaseVO {

    @Schema(description = "主键ID")
    private Long bannerId;

    @Schema(description = "简介")
    private String desc;

    @Schema(description = "轮播图链接")
    private String link;

    @Schema(description = "图片地址")
    private String img;

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

    @Override
    public String toString() {
        return "BannerVO{" +
                "bannerId=" + bannerId +
                ", desc='" + desc + '\'' +
                ", link='" + link + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}