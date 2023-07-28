package per.ccm.ygmall.payment.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import per.ccm.ygmall.database.vo.PageVO;
import per.ccm.ygmall.payment.entity.Coupon;
import per.ccm.ygmall.payment.vo.CouponUserVO;
import per.ccm.ygmall.payment.vo.CouponVO;

public interface CouponService extends IService<Coupon> {
    /**
     * 获取优惠券分页信息
     *
     * @param page 分页
     * @return 优惠券分页信息
     * */
    PageVO<CouponVO> getCouponPages(Page<Coupon> page) throws Exception;

    /**
     * 领取优惠券
     *
     * @param couponId 优惠券ID
     * @param userId 用户ID
     * @return 优惠券用户关联信息
     * */
    CouponUserVO receive(Long couponId, Long userId) throws Exception;
}
