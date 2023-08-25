package per.ccm.ygmall.payment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import per.ccm.ygmall.payment.entity.CouponUserLog;
import per.ccm.ygmall.payment.mapper.CouponUserLogMapper;
import per.ccm.ygmall.payment.service.CouponUserLogService;

@Service
public class CouponUserLogServiceImpl extends ServiceImpl<CouponUserLogMapper, CouponUserLog> implements CouponUserLogService {
}
