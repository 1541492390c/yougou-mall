package per.ccm.ygmall.feign.product.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import per.ccm.ygmall.common.basic.config.FeignUrlConfig;
import per.ccm.ygmall.common.basic.response.ResponseEntity;

import java.util.Map;

@FeignClient(value = "yougou-mall-product", contextId = "sku")
@RequestMapping(FeignUrlConfig.FEIGN_INNER_URL + "/product")
public interface SkuFeign {
    /**
     * 更新sku库存
     *
     * @param skuStockMap skuID和库存
     * */
    @PutMapping("/update")
    ResponseEntity<Void> update(@RequestBody Map<Long, Integer> skuStockMap) throws Exception;
}
