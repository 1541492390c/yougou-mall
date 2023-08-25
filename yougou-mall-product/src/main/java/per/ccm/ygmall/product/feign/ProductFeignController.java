package per.ccm.ygmall.product.feign;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import per.ccm.ygmall.feign.product.bo.ProductBO;
import per.ccm.ygmall.feign.product.feign.ProductFeign;
import per.ccm.ygmall.common.basic.response.ResponseEntity;
import per.ccm.ygmall.product.service.ProductService;
import per.ccm.ygmall.product.service.SkuService;

import java.util.List;
import java.util.Map;

@Hidden
@RestController
public class ProductFeignController implements ProductFeign {

    @Autowired
    private ProductService productService;

    @Autowired
    private SkuService skuService;

    @Override
    public ResponseEntity<List<ProductBO>> queryProductBOList(List<Long> skuIdList) throws Exception {
        List<ProductBO> productBOList = productService.getProductBOList(skuIdList);
        return ResponseEntity.success(productBOList);
    }

    @Override
    public ResponseEntity<Void> updateSkuStock(Map<Long, Integer> skuStockMap) throws Exception {
        skuService.updateSkuStock(skuStockMap);
        return ResponseEntity.success();
    }
}
