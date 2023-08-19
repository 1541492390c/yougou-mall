package per.ccm.ygmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.ccm.ygmall.api.product.bo.SkuBO;
import per.ccm.ygmall.common.exception.YougouException;
import per.ccm.ygmall.common.response.ResponseCodeEnum;
import per.ccm.ygmall.common.util.ConvertUtils;
import per.ccm.ygmall.product.dto.SkuDTO;
import per.ccm.ygmall.product.entity.Sku;
import per.ccm.ygmall.product.mapper.SkuMapper;
import per.ccm.ygmall.product.service.SkuService;
import per.ccm.ygmall.product.vo.SkuVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {

    @Autowired
    private SkuMapper skuMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SkuDTO skuDTO) throws Exception {
        LambdaQueryWrapper<Sku> queryWrapper = new LambdaQueryWrapper<>();
        // 判断sku规格是否存在
        if (skuMapper.exists(queryWrapper.eq(Sku::getSpecs, skuDTO.getSpecs()))) {
            throw new YougouException(ResponseCodeEnum.PRODUCT_ERROR_C4001);
        }
        Sku sku = ConvertUtils.convertProperties(skuDTO, Sku.class);
        skuMapper.insert(sku);
    }

    @Override
    public List<SkuVO> getSkuListByProductId(Long productId) {
        List<Sku> skuList = skuMapper.selectList(new LambdaQueryWrapper<Sku>().eq(Sku::getProductId, productId));
        return ConvertUtils.converList(skuList, SkuVO.class);
    }

    @Override
    public List<SkuBO> getSkuBOList(List<Long> skuIdList) {
        List<Sku> skuList = skuMapper.selectBatchIds(skuIdList);

        List<SkuBO> skuBOList = new ArrayList<>();
        for (Sku sku : skuList) {
            // 设置sku内部传输数据
            SkuBO skuBO = new SkuBO();
            skuBO.setSkuId(sku.getSkuId());
            skuBO.setProductId(sku.getProductId());
            skuBO.setSkuStock(sku.getSkuStock());
            skuBO.setPrice(sku.getPrice());
            skuBO.setDiscountPrice(sku.getDiscountPrice());
            skuBO.setSpecs(sku.getSpecs());
            skuBOList.add(skuBO);
        }
        return skuBOList;
    }

    @Override
    public void updateSkuStock(Map<Long, Integer> map) {
        for (Long skuId: map.keySet()) {
            Sku sku = skuMapper.selectById(skuId);
            sku.setSkuStock(sku.getSkuStock() + map.get(skuId));
            // 库存不足
            if (sku.getSkuStock() < 0) {
                throw new YougouException(ResponseCodeEnum.PRODUCT_ERROR_C4002);
            }
            skuMapper.updateById(sku);
        }
    }
}
