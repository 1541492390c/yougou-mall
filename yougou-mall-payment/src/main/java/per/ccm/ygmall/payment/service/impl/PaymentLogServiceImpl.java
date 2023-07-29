package per.ccm.ygmall.payment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import per.ccm.ygmall.payment.entity.PaymentLog;
import per.ccm.ygmall.payment.mapper.PaymentLogMapper;
import per.ccm.ygmall.payment.service.PaymentLogService;

@Service
public class PaymentLogServiceImpl extends ServiceImpl<PaymentLogMapper, PaymentLog> implements PaymentLogService {
}
