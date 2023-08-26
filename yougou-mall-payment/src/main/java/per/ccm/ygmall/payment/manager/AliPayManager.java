package per.ccm.ygmall.payment.manager;

import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.GoodsDetail;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.feign.order.bo.OrderBO;
import per.ccm.ygmall.feign.order.bo.OrderItemBO;
import per.ccm.ygmall.feign.order.feign.OrderFeign;
import per.ccm.ygmall.common.basic.exception.YougouException;
import per.ccm.ygmall.common.basic.response.ResponseCodeEnum;
import per.ccm.ygmall.common.basic.response.ResponseEntity;
import per.ccm.ygmall.payment.entity.CouponUser;
import per.ccm.ygmall.payment.entity.CouponUserLog;
import per.ccm.ygmall.payment.entity.PaymentLog;
import per.ccm.ygmall.payment.enums.CouponUserStateEnum;
import per.ccm.ygmall.payment.enums.PaymentStateEnum;
import per.ccm.ygmall.payment.service.CouponUserLogService;
import per.ccm.ygmall.payment.service.CouponUserService;
import per.ccm.ygmall.payment.service.PaymentLogService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class AliPayManager {

    @Autowired
    private AlipayClient alipayClient;

    @Autowired
    private OrderFeign orderFeign;

    @Autowired
    private PaymentLogService paymentLogService;

    @Autowired
    private CouponUserLogService couponUserLogService;

    @Autowired
    private CouponUserService couponUserService;

    @Value("${alipay.alipay-public-key}")
    private String aliPayPublicKey;

    @Value("${alipay.sign-type}")
    private String signType;

    @Value("${alipay.return-url}")
    private String returnUrl;

    @Value("${alipay.notify-url}")
    private String notifyUrl;

    public String payment(Long orderId) throws Exception {
        ResponseEntity<OrderBO> response = orderFeign.getOrderBOById(orderId);
        // 内部请求错误
        if (!response.responseSuccess()) {
            throw new YougouException(ResponseCodeEnum.SERVER_ERROR_00001);
        }
        // 获取订单内部传输数据
        OrderBO orderBO = response.getData();
        // 构建支付宝支付请求
        AlipayTradePagePayRequest alipayTradePagePayRequest = new AlipayTradePagePayRequest();
        // 设置返回地址
        alipayTradePagePayRequest.setReturnUrl(returnUrl);
        // 设置异步通知地址
        alipayTradePagePayRequest.setNotifyUrl(notifyUrl);
        // 请求参数
        AlipayTradePagePayModel bizModel = new AlipayTradePagePayModel();
        bizModel.setOutTradeNo(orderBO.getOrderNo());
        bizModel.setTotalAmount(orderBO.getPayAmount().toString());
        bizModel.setSubject("优购商城");
        bizModel.setProductCode("FAST_INSTANT_TRADE_PAY");
        // 商品详情列表
        List<GoodsDetail> goodsDetailList = this.getGoodsDetails(orderBO);
        // 设置商品详情
        bizModel.setGoodsDetail(goodsDetailList);
        // 设置请求信息
        alipayTradePagePayRequest.setBizModel(bizModel);
        // 发送请求
        AlipayTradePagePayResponse alipayTradePagePayResponse = alipayClient.pageExecute(alipayTradePagePayRequest);
        if (alipayTradePagePayResponse.isSuccess()) {
            return alipayTradePagePayResponse.getBody();
        } else {
            throw new YougouException(ResponseCodeEnum.SERVER_ERROR_00003);
        }
    }

    @GlobalTransactional(rollbackFor = Exception.class)
    public void verity(Map<String, String> params) throws Exception {
        boolean isValid = AlipaySignature.verifyV1(params, aliPayPublicKey, "UTF-8", signType);
        // 验签成功记录支付信息
        if (isValid) {
            PaymentLog paymentLog = new PaymentLog();
            paymentLog.setState(PaymentStateEnum.SUCCESS.getValue());
            paymentLog.setOrderNo(params.get("out_trade_no"));
            paymentLog.setTotalAmount(new BigDecimal(params.get("buyer_pay_amount")));
            paymentLogService.save(paymentLog);

            // 查看该订单是否使用优惠券
            CouponUserLog couponUserLog = couponUserLogService.getOne(new LambdaQueryWrapper<CouponUserLog>().eq(CouponUserLog::getIsPay, Boolean.FALSE).eq(CouponUserLog::getOrderNo, params.get("out_trade_no")));
            // 使用了优惠券,更新用户优惠券状态和用户优惠券使用记录
            if (!ObjectUtils.isEmpty(couponUserLog)) {
                couponUserLog.setIsPay(Boolean.TRUE);
                couponUserLogService.updateById(couponUserLog);

                // 更新用户优惠券
                CouponUser couponUser = couponUserService.getById(couponUserLog.getCouponUserId());
                couponUser.setState(CouponUserStateEnum.USED.getValue());
                couponUserService.updateById(couponUser);
            }

            // 更新订单为已支付
            ResponseEntity<Void> response = orderFeign.paySuccess(params.get("out_trade_no"));
            // 抛异常回滚
            if (!response.responseSuccess()) {
                throw new YougouException(ResponseCodeEnum.SERVER_ERROR_00001);
            }
        } else {
            throw new YougouException(ResponseCodeEnum.SERVER_ERROR_00003);
        }
    }

    /**
     * 创建商品详情列表
     *
     * @param orderBO 商品内部传输数据
     * */
    private List<GoodsDetail> getGoodsDetails(OrderBO orderBO) {
        List<GoodsDetail> goodsDetailList = new ArrayList<>();
        for (OrderItemBO orderItemBO : orderBO.getOrderItemBOList()) {
            GoodsDetail goodsDetail = new GoodsDetail();
            goodsDetail.setGoodsId(String.valueOf(orderItemBO.getProductId()));
            goodsDetail.setPrice(String.valueOf(orderItemBO.getTotalAmount()));
            goodsDetail.setQuantity(Long.valueOf(orderItemBO.getQuantity()));
            goodsDetail.setGoodsName(orderItemBO.getProductName());
            goodsDetailList.add(goodsDetail);
        }
        return goodsDetailList;
    }
}
