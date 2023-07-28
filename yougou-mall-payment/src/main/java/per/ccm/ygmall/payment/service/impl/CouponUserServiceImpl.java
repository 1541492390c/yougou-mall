package per.ccm.ygmall.payment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.ccm.ygmall.payment.entity.CouponUser;
import per.ccm.ygmall.payment.mapper.CouponUserMapper;
import per.ccm.ygmall.payment.service.CouponUserService;
import per.ccm.ygmall.payment.vo.CouponUserVO;

import java.util.List;

@Service
public class CouponUserServiceImpl extends ServiceImpl<CouponUserMapper, CouponUser> implements CouponUserService {

    @Autowired
    private CouponUserMapper couponUserMapper;

    @Override
    public List<CouponUserVO> getCouponUserListByUserId(Long userId) {
        return couponUserMapper.selectCouponUserListByUserId(userId);
    }

    @Override
    public Boolean isReceive(Long couponId, Long userId) {
        LambdaQueryWrapper<CouponUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CouponUser::getCouponId, couponId).eq(CouponUser::getUserId, userId);
        return couponUserMapper.exists(queryWrapper);
    }
}
