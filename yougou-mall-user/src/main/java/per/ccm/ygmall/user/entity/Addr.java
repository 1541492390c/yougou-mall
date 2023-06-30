package per.ccm.ygmall.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.database.entity.BaseEntity;

/**
 * 收获地址
 * */
@Getter
@Setter
@ToString
@TableName("addr")
public class Addr extends BaseEntity {

    /**
     * 主键ID
     * */
    @TableId(type = IdType.AUTO)
    private Long addrId;

    /**
     * 用户ID
     * */
    private Long userId;

    /**
     * 收货人
     * */
    private String consignee;

    /**
     * 联系电话
     * */
    private String telephone;

    /**
     * 省份
     * */
    private String province;

    /**
     * 城市
     * */
    private String city;

    /**
     * 区、县
     * */
    private String district;

    /**
     * 详细地址
     * */
    private String detailedAddr;

    /**
     * 是否默认地址
     * */
    private Boolean isDefault;
}
