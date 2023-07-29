package per.ccm.ygmall.payment.task;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import per.ccm.ygmall.payment.entity.Coupon;
import per.ccm.ygmall.payment.service.CouponService;

/**
 * 优惠券相关定时任务
 * */
@Slf4j
@Component
@EnableScheduling
public class CouponTask {

    @Autowired
    private CouponService couponService;

    /**
     * 每天0点重置优惠券领取数量为0
     * */
    @Scheduled(cron = "0 0 0 * * ?")
    private void resetCouponTask() {
        log.info("已将优惠券领取数量重置为0");
        couponService.update(new LambdaUpdateWrapper<Coupon>().set(Coupon::getTakeCount, 0));
    }
}
