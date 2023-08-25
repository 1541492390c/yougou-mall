package per.ccm.ygmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.ccm.ygmall.common.basic.util.ConvertUtils;
import per.ccm.ygmall.product.entity.SecKill;
import per.ccm.ygmall.product.mapper.SecKillMapper;
import per.ccm.ygmall.product.service.SecKillService;
import per.ccm.ygmall.product.vo.SecKillVO;

import java.util.List;

@Service
public class SecKillServiceImpl extends ServiceImpl<SecKillMapper, SecKill> implements SecKillService {

    @Autowired
    private SecKillMapper secKillMapper;

    @Override
    public List<SecKillVO> getSecKillList() {
        List<SecKill> secKillList = secKillMapper.selectList(new LambdaQueryWrapper<>());
        return ConvertUtils.converList(secKillList, SecKillVO.class);
    }
}
