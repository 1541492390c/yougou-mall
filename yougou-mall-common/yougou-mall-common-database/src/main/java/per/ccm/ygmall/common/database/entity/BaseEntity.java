package per.ccm.ygmall.common.database.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class BaseEntity implements Serializable {

    /**
     * 创建时间
     * */
    protected Date createTime;

    /**
     * 更新时间
     * */
    protected Date updateTime;
}
