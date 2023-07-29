package per.ccm.ygmall.payment.controller.admin;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("adminPaymentController")
@RequestMapping("/admin/payment")
@PreAuthorize("hasAnyRole(@roleConfig.SUPER_ADMIN, @roleConfig.COMMON_ADMIN)")
@Tag(name = "支付接口(管理员)", description = "支付接口(管理员)")
public class PaymentController {
}
