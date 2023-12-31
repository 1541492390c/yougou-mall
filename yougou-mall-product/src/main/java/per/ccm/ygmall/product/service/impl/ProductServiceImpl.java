package per.ccm.ygmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.feign.product.bo.ProductBO;
import per.ccm.ygmall.feign.product.bo.SkuBO;
import per.ccm.ygmall.common.cache.cache.CacheNames;
import per.ccm.ygmall.common.basic.exception.YougouException;
import per.ccm.ygmall.common.basic.response.ResponseCodeEnum;
import per.ccm.ygmall.common.basic.util.ConvertUtils;
import per.ccm.ygmall.common.basic.vo.PageVO;
import per.ccm.ygmall.product.dto.AttrDTO;
import per.ccm.ygmall.product.dto.ProductDTO;
import per.ccm.ygmall.product.dto.SkuDTO;
import per.ccm.ygmall.product.entity.Product;
import per.ccm.ygmall.product.mapper.ProductMapper;
import per.ccm.ygmall.product.service.AttrService;
import per.ccm.ygmall.product.service.ProductService;
import per.ccm.ygmall.product.service.SkuService;
import per.ccm.ygmall.product.vo.ProductVO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private AttrService attrService;

    @Autowired
    private SkuService skuService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(cacheNames = CacheNames.PRODUCT_CACHE_NAME, allEntries = true)
    public void save(ProductDTO productDTO) throws Exception {
        // 判断商品名称是否存在
        if (this.isExist(productDTO)) {
            throw new YougouException(ResponseCodeEnum.PRODUCT_ERROR_C1001);
        }

        // 属性值列表或sku列表为空
        if (ObjectUtils.isEmpty(productDTO.getAttrList()) || ObjectUtils.isEmpty(productDTO.getSkuList())) {
            throw new YougouException(ResponseCodeEnum.SERVER_ERROR_00002);
        }

        // 保存商品
        Product product = ConvertUtils.convertProperties(productDTO, Product.class);
        productMapper.insert(product);
        // 属性列表写入商品主键ID
        List<AttrDTO> attrDTOList = productDTO.getAttrList();
        attrDTOList.forEach(item -> item.setProductId(product.getProductId()));
        // 批量保存属性
        attrService.batchSave(attrDTOList);

        // sku列表写入商品主键ID
        List<SkuDTO> skuDTOList = productDTO.getSkuList();
        skuDTOList.forEach(item -> item.setProductId(product.getProductId()));
        // 批量保存sku
        skuService.batchSave(skuDTOList);
    }

    @Override
    public List<ProductBO> getProductBOList(List<Long> skuIdList) throws Exception {
        List<SkuBO> skuBOList = skuService.getSkuBOList(skuIdList);

        // 获取商品ID列表
        List<Long> productIdList = skuBOList.stream().distinct().map(SkuBO::getProductId).collect(Collectors.toList());
        // 获取商品列表
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        List<Product> productList = productMapper.selectList(queryWrapper.in(Product::getProductId, productIdList));
        // 获取商品内部传输数据列表
        List<ProductBO> productBOList = ConvertUtils.converList(productList, ProductBO.class);

        // 根据商品ID分组
        Map<Long, List<SkuBO>> map = skuBOList.stream().collect(Collectors.groupingBy(SkuBO::getProductId));
        // 设置商品sku列表
        for (ProductBO productBO : productBOList) {
            List<SkuBO> skuBOS = map.get(productBO.getProductId());
            productBO.setSkuBOList(skuBOS);
        }
        return productBOList;
    }

    @Override
    @Cacheable(cacheNames = CacheNames.PRODUCT_CACHE_NAME, key = "'recommended_list'", sync = true)
    public List<ProductVO> getRecommendedProductList() {
        return productMapper.selectRecommendedProductList();
    }

    @Override
    @Cacheable(
            cacheNames = CacheNames.PRODUCT_CACHE_NAME, 
            key = "#page.current + ':' + #page.size + #categoryNode + ':' + #isDiscount + ':' + #recommended", sync = true)
    public PageVO<ProductVO> getProductPages(String name, String categoryNode, Boolean isDiscount, Boolean recommended, Page<Product> page) {
        Page<ProductVO> pageInfo = productMapper.selectProductPages(name, categoryNode, isDiscount, recommended, page);
        return new PageVO<>(pageInfo.getTotal(), pageInfo.getRecords());
    }

    @Override
    @Cacheable(cacheNames = CacheNames.PRODUCT_CACHE_NAME, key = "#productId", sync = true)
    public ProductVO getProductByProductId(Long productId) {
        return productMapper.selectProductByProductId(productId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(cacheNames = CacheNames.PRODUCT_CACHE_NAME, allEntries = true)
    public void update(ProductDTO productDTO) throws Exception {
        Product product = ConvertUtils.convertProperties(productDTO, Product.class);
        // 更新商品信息
        productMapper.updateById(product);
        // 更新sku信息
        skuService.updateDiscountPrice(product.getProductId(), productDTO.getDiscount());
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
