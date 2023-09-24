package per.ccm.ygmall.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import per.ccm.ygmall.product.entity.Product;
import per.ccm.ygmall.product.vo.ProductVO;

import java.util.List;

@Repository
public interface ProductMapper extends BaseMapper<Product> {
    List<ProductVO> selectRecommendedProductList();

    Page<ProductVO> selectProductPages(@Param("name") String name,@Param("categoryNode") String categoryNode,
                                       @Param("isDiscount") Boolean isDiscount, @Param("recommended") Boolean recommended, Page<Product> page);

    ProductVO selectProductByProductId(Long productId);
}
