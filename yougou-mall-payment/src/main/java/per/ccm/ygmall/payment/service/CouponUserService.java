package per.ccm.ygmall.payment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import per.ccm.ygmall.payment.entity.CouponUser;
import per.ccm.ygmall.payment.vo.CouponUserVO;

import java.util.List;

public interface CouponUserService extends IService<CouponUser> {
    /**
     * 根据用户ID获取用户优惠券信息列表
     *
     * @param userId 用户ID
     * @return 用户优惠券信息列表
     * */
    List<CouponUserVO> getCouponUserListByUserId(Long userId) throws Exception;

    /**
     * 是否已经领取当前优惠券
     *
     * @param couponId 优惠券ID
     * @param userId 用户ID
     * @return 是否已经领取当前优惠券
     * */
    Boolean isReceive(Long couponId, Long userId) throws Exception;
}
