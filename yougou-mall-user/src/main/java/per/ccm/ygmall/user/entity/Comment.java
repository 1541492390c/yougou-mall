package per.ccm.ygmall.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.database.entity.BaseEntity;

/**
 * 评价
 * */
@Getter
@Setter
@ToString
@TableName("comment")
public class Comment extends BaseEntity {

    /**
     * 主键ID
     * */
    @TableId(type = IdType.AUTO)
    private Long commentId;

    /**
     * 用户ID
     * */
    private Long userId;

    /**
     * 商品ID
     * */
    private Long productId;

    /**
     * 订单ID
     * */
    private Long orderId;

    /**
     * 订单项ID
     * */
    private Long orderItemId;

    /**
     * 评分
     * */
    private Double rate;

    /**
     * 评价内容
     * */
    private String text;
}
