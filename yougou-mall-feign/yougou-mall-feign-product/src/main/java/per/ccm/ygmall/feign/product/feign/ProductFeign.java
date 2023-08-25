package per.ccm.ygmall.feign.product.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import per.ccm.ygmall.feign.product.bo.ProductBO;
import per.ccm.ygmall.common.basic.config.FeignUrlConfig;
import per.ccm.ygmall.common.basic.response.ResponseEntity;

import java.util.List;

@FeignClient(value = "yougou-mall-product", contextId = "product")
@RequestMapping(FeignUrlConfig.FEIGN_INNER_URL + "/product")
public interface ProductFeign {
    /**
     * 获取商品内部传输数据列表
     *
     * @param skuIdList skuID列表
     * */
    @PostMapping("/list")
    ResponseEntity<List<ProductBO>> queryProductBOList(@RequestBody List<Long> skuIdList) throws Exception;
}
