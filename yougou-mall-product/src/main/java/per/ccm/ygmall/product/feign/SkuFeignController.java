package per.ccm.ygmall.product.feign;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import per.ccm.ygmall.common.basic.response.ResponseEntity;
import per.ccm.ygmall.feign.product.feign.SkuFeign;
import per.ccm.ygmall.product.service.SkuService;

import java.util.Map;

@Hidden
@RestController
public class SkuFeignController implements SkuFeign {

    @Autowired
    private SkuService skuService;

    @Override
    public ResponseEntity<Void> update(Map<Long, Integer> skuStockMap) throws Exception {
        skuService.updateSkuStock(skuStockMap);
        return ResponseEntity.success();
    }
}
