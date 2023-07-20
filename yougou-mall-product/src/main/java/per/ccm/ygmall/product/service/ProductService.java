package per.ccm.ygmall.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import per.ccm.ygmall.api.product.bo.ProductBO;
import per.ccm.ygmall.database.vo.PageVO;
import per.ccm.ygmall.product.dto.ProductDTO;
import per.ccm.ygmall.product.entity.Product;
import per.ccm.ygmall.product.vo.ProductVO;

import java.util.List;

public interface ProductService extends IService<Product> {
    /**
     * 保存商品信息
     *
     * @param productDTO 商品传输数据
     * */
    void save(ProductDTO productDTO) throws Exception;

    /**
     * 获取推荐的商品信息
     *
     * @return 推荐商品商品信息列表
     * */
    List<ProductVO> getRecommendedProductList() throws Exception;

    /**
     * 获取商品内部传输数据列表
     *
     * @param skuIdList skuID列表
     * @return 商品内部传输数据列表
     * */
    List<ProductBO> getProductBOList(List<Long> skuIdList) throws Exception;

    /**
     * 分页获取spu信息
     *
     * @param categoryNode 分类节点
     * @param recommended 是否推荐
     * @param page 分页信息
     * @return 商品信息分页列表
     * */
    PageVO<ProductVO> getProductPages(String categoryNode, Boolean recommended, Page<Product> page) throws Exception;

    /**
     * 根据商品ID获取商品信息
     *
     * @param productId 商品ID
     * @return 商品信息
     * */
    ProductVO getProductByProductId(Long productId) throws Exception;

    /**
     * 更新商品商品信息
     *
     * @param productDTO 商品传输数据
     * */
    void update(ProductDTO productDTO) throws Exception;
}
