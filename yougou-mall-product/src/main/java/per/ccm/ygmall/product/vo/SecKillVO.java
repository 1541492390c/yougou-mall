package per.ccm.ygmall.product.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.vo.BaseVO;

/**
 * 秒杀活动场次信息
 * */
@Getter
@Setter
@ToString
public class SecKillVO extends BaseVO {

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
