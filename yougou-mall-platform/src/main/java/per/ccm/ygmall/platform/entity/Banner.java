package per.ccm.ygmall.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import per.ccm.ygmall.common.entity.BaseEntity;

/**
 * 轮播图
 * */
@TableName("banner")
public class Banner extends BaseEntity {

    /**
     * 主键ID
     * */
    @TableId(type = IdType.AUTO)
    private Long bannerId;

    /**
     * 轮播图类型 1-PC端 2-移动端 3-小程序端
     * */
    @TableField("type")
    private Integer type;

    /**
     * 简介
     * */
    @TableField("desc")
    private String desc;

    /**
     * 轮播图链接
     * */
    @TableField("link")
    private String link;

    /**
     * 图片地址
     * */
    @TableField("img")
    private String img;

    /**
     * 所属页面
     * */
    @TableField("page")
    private String page;

    @TableField("enabled")
    private Boolean enabled;

    public Long getBannerId() {
        return bannerId;
    }

    public void setBannerId(Long bannerId) {
        this.bannerId = bannerId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
        return "Banner{" +
                "bannerId=" + bannerId +
                ", type=" + type +
                ", desc='" + desc + '\'' +
                ", link='" + link + '\'' +
                ", img='" + img + '\'' +
                ", page='" + page + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
