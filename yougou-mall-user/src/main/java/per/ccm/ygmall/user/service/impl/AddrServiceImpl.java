package per.ccm.ygmall.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.ccm.ygmall.common.exception.YougouException;
import per.ccm.ygmall.common.response.ResponseCodeEnum;
import per.ccm.ygmall.common.util.ConvertUtils;
import per.ccm.ygmall.user.dto.AddrDTO;
import per.ccm.ygmall.user.entity.Addr;
import per.ccm.ygmall.user.mapper.AddrMapper;
import per.ccm.ygmall.user.service.AddrService;
import per.ccm.ygmall.user.vo.AddrVO;

import java.util.List;

@Service
public class AddrServiceImpl implements AddrService {

    @Autowired
    private AddrMapper addrMapper;

    @Override
    public void save(AddrDTO addrDTO) throws Exception {
        LambdaQueryWrapper<Addr> queryWrapper = new LambdaQueryWrapper<>();
        // 一个用户最多添加5个收货地址
        if (addrMapper.selectCount(queryWrapper.eq(Addr::getUserId, addrDTO.getUserId())) >= 5) {
            throw new YougouException(ResponseCodeEnum.USER_ERROR_A2001);
        }
        Addr addr = ConvertUtils.convertProperties(addrDTO, Addr.class);
        addrMapper.insert(addr);
    }

    @Override
    public List<AddrVO> getAddrList(Long userId) {
        LambdaQueryWrapper<Addr> queryWrapper = new LambdaQueryWrapper<>();
        List<Addr> addrList = addrMapper.selectList(queryWrapper.eq(Addr::getUserId, userId));
        return ConvertUtils.converList(addrList, AddrVO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(AddrDTO addrDTO) {
        Addr addr = ConvertUtils.convertProperties(addrDTO, Addr.class);

        // 设置该收货地址设为默认,则取消用户其他默认收货地址
        if (addr.getIsDefault()) {
            LambdaQueryWrapper<Addr> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Addr::getUserId, addrDTO.getUserId()).eq(Addr::getIsDefault, Boolean.TRUE);
            Addr defaultAddr = addrMapper.selectOne(queryWrapper);
            addrMapper.update(defaultAddr, new LambdaUpdateWrapper<Addr>().set(Addr::getIsDefault, Boolean.FALSE));
        }
        addrMapper.updateById(addr);
    }

    @Override
    public void delete(Long addrId) {
        addrMapper.deleteById(addrId);
    }
}
