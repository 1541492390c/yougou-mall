package per.ccm.ygmall.product.service;

import per.ccm.ygmall.product.dto.SkuSpecsDTO;

import java.util.List;

public interface SkuSpecsService {
    /**
     * 判断sku规格是否存在
     *
     * @param spuId spuID
     * @param skuSpecsDTOList sku规格传输数据列表
     * @return sku规格是否存在
     * */
    Boolean isExist(Long spuId, List<SkuSpecsDTO> skuSpecsDTOList) throws Exception;

    /**
     * 批量保存sku规格
     *
     * @param skuSpecsDTOList sku规格传输数据列表
     * */
    void batchSave(List<SkuSpecsDTO> skuSpecsDTOList) throws Exception;
}