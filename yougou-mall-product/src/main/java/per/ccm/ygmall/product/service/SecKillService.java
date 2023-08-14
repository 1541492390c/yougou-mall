package per.ccm.ygmall.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import per.ccm.ygmall.product.entity.SecKill;
import per.ccm.ygmall.product.vo.SecKillVO;

import java.util.List;

public interface SecKillService extends IService<SecKill> {
    /**
     * 获取全部秒杀活动场次信息列表
     *
     * @return 秒杀活动场次信息列表
     * */
    List<SecKillVO> getSecKillList() throws Exception;
}
