package per.ccm.ygmall.payment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.ccm.ygmall.common.basic.exception.YougouException;
import per.ccm.ygmall.common.basic.response.ResponseCodeEnum;
import per.ccm.ygmall.common.basic.util.ConvertUtils;
import per.ccm.ygmall.common.basic.vo.PageVO;
import per.ccm.ygmall.payment.dto.CouponDTO;
import per.ccm.ygmall.payment.entity.Coupon;
import per.ccm.ygmall.payment.entity.CouponUser;
import per.ccm.ygmall.payment.enums.CouponUserStateEnum;
import per.ccm.ygmall.payment.mapper.CouponMapper;
import per.ccm.ygmall.payment.service.CouponService;
import per.ccm.ygmall.payment.service.CouponUserService;
import per.ccm.ygmall.payment.vo.CouponUserVO;
import per.ccm.ygmall.payment.vo.CouponVO;

import java.util.Date;
import java.util.List;

@Service
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon> implements CouponService {

    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private CouponUserService couponUserService;

    @Autowired
    private RLock rLock;

    @Override
    public PageVO<CouponVO> getCouponPages(Page<Coupon> page) {
        Page<Coupon> pageInfo = couponMapper.selectPage(page, new LambdaQueryWrapper<>());
        List<CouponVO> couponList = ConvertUtils.converList(pageInfo.getRecords(), CouponVO.class);
        return new PageVO<>(pageInfo.getTotal(), couponList);
    }

    @Override
    public CouponUserVO receive(Long couponId, Long userId) throws Exception {
        rLock.lock();
        try {
            // 已领取该优惠券
            if (couponUserService.isReceive(couponId, userId)) {
                throw new YougouException(ResponseCodeEnum.PAYMENT_ERROR_E0001);
            }

            Coupon coupon = couponMapper.selectById(couponId);
            // 优惠券已领取完
            if (coupon.getTakeCount() >= coupon.getQuota()) {
                throw new YougouException(ResponseCodeEnum.PAYMENT_ERROR_E0002);
            }
            CouponUser couponUser = new CouponUser();
            // 设置优惠券ID
            couponUser.setCouponId(coupon.getCouponId());
            // 设置用户ID
            couponUser.setUserId(userId);
            // 设置当前状态
            couponUser.setState(CouponUserStateEnum.WAIT_USE.getValue());
            // 设置领取时间
            couponUser.setReceiveTime(new Date());
            // 设置过期时间
            couponUser.setExpiredTime(new Date(new Date().getTime() + coupon.getExpired() * 24 * 60 * 60 * 1000));
            couponUserService.save(couponUser);
            // 更新优惠券领取数量
            coupon.setTakeCount(coupon.getTakeCount() + 1);
            couponMapper.updateById(coupon);

            // 将优惠券用户关联信息转为VO
            CouponUserVO couponUserVO = ConvertUtils.convertProperties(couponUser, CouponUserVO.class);
            // 将优惠券信息转为VO
            CouponVO couponVO = ConvertUtils.convertProperties(coupon, CouponVO.class);
            couponUserVO.setCoupon(couponVO);
            return couponUserVO;
        } finally {
            rLock.unlock();
        }
    }

    @Override
    public void update(CouponDTO couponDTO) {
        Coupon coupon = ConvertUtils.convertProperties(couponDTO, Coupon.class);
        couponMapper.updateById(coupon);
    }
}
