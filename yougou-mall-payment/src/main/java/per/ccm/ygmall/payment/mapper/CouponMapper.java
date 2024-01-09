package per.ccm.ygmall.payment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import per.ccm.ygmall.payment.entity.Coupon;
import per.ccm.ygmall.payment.vo.CouponVO;

@Repository
public interface CouponMapper extends BaseMapper<Coupon> {
    CouponVO selectCouponById(Long couponId);
}
