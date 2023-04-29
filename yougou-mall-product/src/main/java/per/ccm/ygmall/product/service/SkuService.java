package per.ccm.ygmall.product.service;

import per.ccm.ygmall.product.vo.SkuVO;

import java.util.List;

public interface SkuService {
    List<SkuVO> getSkuList(Long spuId) throws Exception;
}
