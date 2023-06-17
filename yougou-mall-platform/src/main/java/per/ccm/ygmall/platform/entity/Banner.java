package per.ccm.ygmall.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import per.ccm.ygmall.database.entity.BaseEntity;

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
    private Integer type;

    /**
     * 简介
     * */
    private String description;

    /**
     * 轮播图链接
     * */
    private String link;

    /**
     * 图片地址
     * */
    private String img;

    /**
     * 所属页面
     * */
    private String page;

    /**
     * 是否启用 0-否 1-是
     * */
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", img='" + img + '\'' +
                ", page='" + page + '\'' +
                ", enabled=" + enabled +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
