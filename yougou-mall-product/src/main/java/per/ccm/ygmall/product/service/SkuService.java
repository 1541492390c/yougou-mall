package per.ccm.ygmall.product.service;

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
     *
     * */
    List<SkuVO> getSkuListBySpuId(Long spuId) throws Exception;
}
