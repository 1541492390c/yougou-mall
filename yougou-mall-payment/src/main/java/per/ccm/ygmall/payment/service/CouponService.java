package per.ccm.ygmall.payment.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import per.ccm.ygmall.common.basic.vo.PageVO;
import per.ccm.ygmall.payment.dto.CouponDTO;
import per.ccm.ygmall.payment.entity.Coupon;
import per.ccm.ygmall.payment.vo.CouponUserVO;
import per.ccm.ygmall.payment.vo.CouponVO;

public interface CouponService extends IService<Coupon> {
    /**
     * 保存优惠券信息
     *
     * @param couponDTO 优惠券传输数据
     * */
    void save(CouponDTO couponDTO) throws Exception;

    /**
     * 获取优惠券分页信息
     *
     * @param page 分页
     * @return 优惠券分页信息
     * */
    PageVO<CouponVO> getCouponPages(Page<Coupon> page) throws Exception;

    /**
     * 获取优惠券分页信息
     *
     * @param quota 配额
     * @param expired 过期时间
     * @param page 分页信息
     * @return 优惠券分页信息
     * */
    PageVO<CouponVO> getCouponPages(Integer quota, Integer expired, String categoryNode, Page<Coupon> page) throws Exception;

    /**
     * 领取优惠券
     *
     * @param couponId 优惠券ID
     * @param userId 用户ID
     * @return 优惠券用户关联信息
     * */
    CouponUserVO receive(Long couponId, Long userId) throws Exception;

    /**
     * 更新优惠券信息
     *
     * @param couponDTO 优惠券传输数据
     * */
    void update(CouponDTO couponDTO) throws Exception;
}
