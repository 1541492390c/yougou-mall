package per.ccm.ygmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.cache.cache.CacheNames;
import per.ccm.ygmall.common.exception.YougouException;
import per.ccm.ygmall.common.response.ResponseCode;
import per.ccm.ygmall.common.util.ConvertUtils;
import per.ccm.ygmall.database.vo.PageVO;
import per.ccm.ygmall.product.dto.ProductDTO;
import per.ccm.ygmall.product.entity.Product;
import per.ccm.ygmall.product.mapper.ProductMapper;
import per.ccm.ygmall.product.service.ProductService;
import per.ccm.ygmall.product.vo.ProductVO;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void save(ProductDTO productDTO) {
        // 判断商品名称是否存在
        if (this.isExist(productDTO)) {
            throw new YougouException(ResponseCode.PRODUCT_ERROR_B10001);
        }
        Product product = ConvertUtils.convertProperties(productDTO, Product.class);
        productMapper.insert(product);
    }

    @Override
    @Cacheable(cacheNames = CacheNames.PRODUCT_CACHE_NAME, key = "'recommended-list'", sync = true)
    public List<ProductVO> getRecommendedProductList() {
        return productMapper.selectRecommendedProductList();
    }

    @Override
    public PageVO<ProductVO> getProductPages(String categories, Page<Product> page) {
        IPage<ProductVO> pageInfo = productMapper.selectProductPages(categories, page);
        return new PageVO<>(pageInfo.getTotal(), pageInfo.getRecords());
    }

    @Override
    public void update(ProductDTO productDTO) {
        // 判断商品名称是否存在
        if (this.isExist(productDTO)) {
            throw new YougouException(ResponseCode.PRODUCT_ERROR_B10001);
        }
        Product product = ConvertUtils.convertProperties(productDTO, Product.class);
        productMapper.updateById(product);
    }

    /**
     * 判断当前该商品名称是否存在
     *
     * @param productDTO 商品传输数据
     * @return 是否存在该商品名称
     * */
    private Boolean isExist(ProductDTO productDTO) {
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        Product productExist = productMapper.selectOne(queryWrapper.eq(Product::getName, productDTO.getName()));
        return !ObjectUtils.isEmpty(productExist);
    }
}
