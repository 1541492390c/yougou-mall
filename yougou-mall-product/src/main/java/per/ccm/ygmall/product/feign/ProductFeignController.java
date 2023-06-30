package per.ccm.ygmall.product.feign;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import per.ccm.ygmall.api.product.bo.ProductBO;
import per.ccm.ygmall.api.product.bo.SkuBO;
import per.ccm.ygmall.api.product.feign.ProductFeign;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.product.service.ProductService;

import java.util.List;

@Hidden
@RestController
public class ProductFeignController implements ProductFeign {

    @Autowired
    private ProductService productService;

    @Override
    public ResponseEntity<List<ProductBO>> queryProductBOList(List<Long> skuIdList) throws Exception {
        List<ProductBO> productBOList = productService.getProductBOList(skuIdList);
        return ResponseEntity.success(productBOList);
    }

    @Override
    public ResponseEntity<Void> batchUpdateSku(List<SkuBO> skuBOList) {
        return null;
    }
}