package per.ccm.ygmall.payment.controller.common;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import per.ccm.ygmall.common.basic.response.ResponseEntity;
import per.ccm.ygmall.payment.enums.PaymentTypeEnum;
import per.ccm.ygmall.payment.manager.AliPayManager;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/payment")
@Tag(name = "支付接口", description = "支付接口")
public class PaymentController {

    @Autowired
    private AliPayManager aliPayManager;

    @PostMapping("/pay")
    @Operation(summary = "支付", description = "支付")
    @Parameters({
            @Parameter(name = "order_id", description = "订单ID"),
            @Parameter(name = "payment_type", description = "支付类型")
    })
    public ResponseEntity<?> pay(@RequestParam("order_id") Long orderId, @RequestParam("payment_type") Integer paymentType) throws Exception {
        // 支付宝支付
        if (ObjectUtils.nullSafeEquals(paymentType, PaymentTypeEnum.ALI_PAY.getValue())) {
            String responseBody = aliPayManager.payment(orderId);
            return ResponseEntity.success(responseBody);
        }
        return ResponseEntity.success();
    }

    @PostMapping("alipay_notify")
    public ResponseEntity<Void> alipayNotify(HttpServletRequest request) throws Exception {
        Map<String, String> params = this.getAlipayNotifyParams(request);
        aliPayManager.verity(params);
        return ResponseEntity.success();
    }

    /**
     * 获取支付宝回调信息
     *
     * @param request request
     * @return 待验签信息
     * */
    private Map<String, String> getAlipayNotifyParams(HttpServletRequest request) {
        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (String name : requestParams.keySet()) {
            // 去除sign和sign_type参数
            if (ObjectUtils.nullSafeEquals(name, "sign_type")) {
                continue;
            }
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
//       valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        return params;
    }
}
