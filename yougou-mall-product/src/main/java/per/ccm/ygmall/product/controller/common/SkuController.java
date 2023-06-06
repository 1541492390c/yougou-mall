package per.ccm.ygmall.product.controller.common;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.product.service.SkuService;
import per.ccm.ygmall.product.vo.SkuVO;

import java.util.List;

@RestController
@RequestMapping("/product/sku")
@Tag(name = "sku接口", description = "sku接口")
public class SkuController {

    @Autowired
    private SkuService skuService;

    @GetMapping("/list")
    @Operation(summary = "获取sku信息列表", description = "根据商品ID获取对应的sku列表")
    @Parameter(name = "productId", description = "商品ID")
    public ResponseEntity<List<SkuVO>> getSkuList(@RequestParam("product_id") Long productId) throws Exception {
        List<SkuVO> skuList = skuService.getSkuListByProductId(productId);
        return ResponseEntity.success(skuList);
    }
}
