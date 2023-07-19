package per.ccm.ygmall.product.controller.common;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product/brand")
@Tag(name = "品牌接口", description = "品牌接口")
public class BrandController {
}
