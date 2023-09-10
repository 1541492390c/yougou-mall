package per.ccm.ygmall.payment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import per.ccm.ygmall.feign.payment.bo.CouponUserBO;
import per.ccm.ygmall.payment.dto.QueryAvailableCouponDTO;
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

    /**
     * 查询可用优惠券
     *
     * @param queryAvailableCouponDTO 查询可用优惠券传输数据
     * */
    List<CouponUserVO> getAvailableCoupon(QueryAvailableCouponDTO queryAvailableCouponDTO) throws Exception;

    /**
     * 使用优惠券,记录优惠券使用记录,并返回用户优惠券内部传输数据
     *
     * @param couponUserId 用户优惠券ID
     * @param orderNo 订单号
     * */
    CouponUserBO useCoupon(Long couponUserId, String orderNo) throws Exception;
}
