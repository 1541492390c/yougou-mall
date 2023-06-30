package per.ccm.ygmall.api.product.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import per.ccm.ygmall.api.product.bo.ProductBO;
import per.ccm.ygmall.api.product.bo.SkuBO;
import per.ccm.ygmall.common.config.FeignUrlConfig;
import per.ccm.ygmall.common.response.ResponseEntity;

import java.util.List;

@FeignClient("yougou-mall-product")
@RequestMapping(FeignUrlConfig.FEIGN_INNER_URL + "/product")
public interface ProductFeign {
    /**
     * 获取商品内部传输数据列表
     *
     * @param skuIdList skuID列表
     * */
    @PostMapping("/list")
    ResponseEntity<List<ProductBO>> queryProductBOList(@RequestBody List<Long> skuIdList) throws Exception;

    /**
     * 批量更新sku
     * */
    @PutMapping("/sku/batch_update")
    ResponseEntity<Void> batchUpdateSku(@RequestBody List<SkuBO> skuBOList) throws Exception;
}
