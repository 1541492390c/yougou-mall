package per.ccm.ygmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import per.ccm.ygmall.product.dto.SkuSpecsDTO;
import per.ccm.ygmall.product.entity.SkuSpecs;

import java.util.List;

public interface SkuSpecsService extends IService<SkuSpecs> {
    /**
     * 判断sku规格是否存在
     *
     * @param productId 商品ID
     * @param skuSpecsDTOList sku规格传输数据列表
     * @return sku规格是否存在
     * */
    Boolean isExist(Long productId, List<SkuSpecsDTO> skuSpecsDTOList) throws Exception;

    /**
     * 批量保存sku规格
     *
     * @param skuSpecsDTOList sku规格传输数据列表
     * */
    void batchSave(List<SkuSpecsDTO> skuSpecsDTOList) throws Exception;
}
