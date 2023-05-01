package per.ccm.ygmall.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable {

    /**
     * 创建时间
     * */
    @TableField("create_time")
    protected Date createTime;

    /**
     * 更新时间
     * */
    @TableField("update_time")
    protected Date updateTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
