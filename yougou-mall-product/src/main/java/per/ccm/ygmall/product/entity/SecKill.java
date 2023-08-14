package per.ccm.ygmall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.database.entity.BaseEntity;

/**
 * 秒杀活动场次
 * */
@Getter
@Setter
@ToString
@TableName("sec_kill")
public class SecKill extends BaseEntity {

    /**
     * 主键ID
     * */
    @TableId(type = IdType.AUTO)
    private Long secKillId;

    /**
     * 描述
     * */
    private String description;

    /**
     * 开始时间
     * */
    private String startTime;

    /**
     * 结束时间
     * */
    private String endTime;
}
