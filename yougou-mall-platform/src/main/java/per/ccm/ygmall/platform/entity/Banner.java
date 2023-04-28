package per.ccm.ygmall.platform.entity;

import per.ccm.ygmall.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 轮播图
 * */
@Entity
@Table(name = "platform_banner")
public class Banner extends BaseEntity {

    /**
     * 主键ID
     * */
    @Id
    @Column(name = "platform_banner_id")
    private Long bannerId;

    /**
     * 轮播图类型 1-PC端 2-移动端 3-小程序端
     * */
    @Column(name = "type")
    private Integer type;

    /**
     * 简介
     * */
    @Column(name = "desc")
    private String desc;

    /**
     * 轮播图链接
     * */
    @Column(name = "link")
    private String link;

    /**
     * 图片地址
     * */
    @Column(name = "img")
    private String img;

    /**
     * 所属页面
     * */
    @Column(name = "page")
    private String page;

    @Column(name = "enabled")
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
