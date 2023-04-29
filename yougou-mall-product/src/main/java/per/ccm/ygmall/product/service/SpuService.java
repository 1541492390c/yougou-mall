package per.ccm.ygmall.product.service;

import org.springframework.data.domain.Pageable;
import per.ccm.ygmall.common.vo.PageVO;
import per.ccm.ygmall.product.dto.SpuDTO;
import per.ccm.ygmall.product.vo.SpuVO;

import java.util.List;

public interface SpuService {
    /**
     * 保存spu信息
     *
     * @param spuDTO spu传输数据
     * */
    void save(SpuDTO spuDTO) throws Exception;

    /**
     * 获取推荐的商品信息
     *
     * @return 推荐商品spu信息列表
     * */
    List<SpuVO> getRecommendedSpuList() throws Exception;

    /**
     * 分页获取spu信息
     *
     * @param pageable 分页信息
     * @return 商品信息分页列表
     * */
    PageVO<SpuVO> getSpuPages(Pageable pageable) throws Exception;

    /**
     * 更新商品spu信息
     *
     * @param spuDTO spu传输数据
     * */
    void update(SpuDTO spuDTO) throws Exception;
}
