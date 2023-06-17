package per.ccm.ygmall.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.database.entity.BaseEntity;

/**
 * 轮播图
 * */
@Getter
@Setter
@ToString
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
     * 是否启用
     * */
    @TableLogic(value = "1", delval = "0")
    private Boolean enabled;
}
