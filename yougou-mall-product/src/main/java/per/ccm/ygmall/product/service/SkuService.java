package per.ccm.ygmall.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import per.ccm.ygmall.common.basic.vo.PageVO;
import per.ccm.ygmall.feign.product.bo.SkuBO;
import per.ccm.ygmall.product.dto.SkuDTO;
import per.ccm.ygmall.product.entity.Sku;
import per.ccm.ygmall.product.vo.SkuVO;

import java.util.List;
import java.util.Map;

public interface SkuService extends IService<Sku> {
    /**
     * 批量保存sku列表
     *
     * @param skuDTOList sku传输数据列表
     * */
    void batchSave(List<SkuDTO> skuDTOList) throws Exception;

    /**
     * 分页获取sku信息
     *
     * @param page 分页信息
     * @return 商品信息分页列表
     * */
    PageVO<SkuVO> getSkuPages(Long productId, Page<Sku> page) throws Exception;

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

    /**
     * 更新商品信息
     *
     * @param skuDTO sku传输数据
     * */
    void update(SkuDTO skuDTO) throws Exception;

    /**
     * 订单取消后,返还库存
     *
     * @param skuStockMap 返还的库存和skuID
     * */
    void updateSkuStock(Map<Long, Integer> skuStockMap) throws Exception;

    /**
     * 更新折扣价格
     *
     * @param productId 商品ID
     * @param discount 折扣率
     * */
    void updateDiscountPrice(Long productId, Integer discount) throws Exception;
}
