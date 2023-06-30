package per.ccm.ygmall.product.service;

import per.ccm.ygmall.api.product.bo.SkuBO;
import per.ccm.ygmall.product.dto.SkuDTO;
import per.ccm.ygmall.product.vo.SkuVO;

import java.util.List;

public interface SkuService {
    /**
     * 保存sku信息
     *
     * @param skuDTO sku传输数据
     * */
    void save(SkuDTO skuDTO) throws Exception;

    /**
     * 根据商品ID获取sku信息列表
     *
     * @param productId 商品ID
     * @return sku信息列表
     * */
    List<SkuVO> getSkuListByProductId(Long productId) throws Exception;

    /**
     * 获取sku内部传输数据列表
     *
     * @param skuIdList skuID列表
     * @return sku内部传输数据列表
     * */
    List<SkuBO> getSkuBOList(List<Long> skuIdList) throws Exception;
}
