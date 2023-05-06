package per.ccm.ygmall.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import per.ccm.ygmall.common.vo.PageVO;
import per.ccm.ygmall.product.dto.ProductDTO;
import per.ccm.ygmall.product.entity.Product;
import per.ccm.ygmall.product.vo.ProductVO;

import java.util.List;

public interface ProductService {
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
     * 分页获取spu信息
     *
     * @param categories 分类路径
     * @param page 分页信息
     * @return 商品信息分页列表
     * */
    PageVO<ProductVO> getProductPages(String categories, Page<Product> page) throws Exception;

    /**
     * 更新商品商品信息
     *
     * @param productDTO 商品传输数据
     * */
    void update(ProductDTO productDTO) throws Exception;
}
