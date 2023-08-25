package per.ccm.ygmall.payment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import per.ccm.ygmall.payment.entity.CouponUser;
import per.ccm.ygmall.payment.vo.CouponUserVO;

import java.util.List;

@Repository
public interface CouponUserMapper extends BaseMapper<CouponUser> {
    List<CouponUserVO> selectCouponUserListByUserId(Long userId);

    CouponUserVO selectCouponUserListByCouponUserId(Long couponUserId);
}
