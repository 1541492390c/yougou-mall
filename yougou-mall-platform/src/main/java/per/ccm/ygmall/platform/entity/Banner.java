package per.ccm.ygmall.platform.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.database.entity.BaseEntity;

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
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String description;

    /**
     * 轮播图链接
     * */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
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
    private Boolean enabled;
}
