package per.ccm.ygmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.common.basic.vo.PageVO;
import per.ccm.ygmall.feign.product.bo.SkuBO;
import per.ccm.ygmall.common.basic.exception.YougouException;
import per.ccm.ygmall.common.basic.response.ResponseCodeEnum;
import per.ccm.ygmall.common.basic.util.ConvertUtils;
import per.ccm.ygmall.product.dto.SkuDTO;
import per.ccm.ygmall.product.entity.Sku;
import per.ccm.ygmall.product.mapper.SkuMapper;
import per.ccm.ygmall.product.service.SkuService;
import per.ccm.ygmall.product.vo.SkuVO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {

    @Autowired
    private SkuMapper skuMapper;

    @Override
    public void batchSave(List<SkuDTO> skuDTOList) {
        LambdaQueryWrapper<Sku> queryWrapper = new LambdaQueryWrapper<>();

        for (SkuDTO skuDTO : skuDTOList) {
            // 判断sku规格是否存在
            if (skuMapper.exists(queryWrapper.eq(Sku::getProductId, skuDTO.getProductId()).eq(Sku::getSpecs, skuDTO.getSpecs()))) {
                throw new YougouException(ResponseCodeEnum.PRODUCT_ERROR_C4001);
            }
            // 将sku传输数据转为实体
            Sku sku = ConvertUtils.convertProperties(skuDTO, Sku.class);
            // 保存sku
            skuMapper.insert(sku);
        }
    }

    @Override
    public PageVO<SkuVO> getSkuPages(Long productId, Page<Sku> page) {
        Page<Sku> pageInfo = skuMapper.selectPage(page, new LambdaQueryWrapper<Sku>().eq(Sku::getProductId, productId));
        List<SkuVO> skuList = ConvertUtils.converList(pageInfo.getRecords(), SkuVO.class);
        return new PageVO<>(pageInfo.getTotal(), skuList);
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
    public void update(SkuDTO skuDTO) throws Exception {
        // skuID为空
        if (ObjectUtils.isEmpty(skuDTO.getSkuId())) {
            throw new YougouException(ResponseCodeEnum.SERVER_ERROR_00002);
        }
        Sku sku = ConvertUtils.convertProperties(skuDTO, Sku.class);
        skuMapper.updateById(sku);
    }

    @Override
    public void updateSkuStock(Map<Long, Integer> map) {
        for (Long skuId : map.keySet()) {
            Sku sku = skuMapper.selectById(skuId);
            sku.setSkuStock(sku.getSkuStock() + map.get(skuId));
            // 库存不足
            if (sku.getSkuStock() < 0) {
                throw new YougouException(ResponseCodeEnum.PRODUCT_ERROR_C4002);
            }
            skuMapper.updateById(sku);
        }
    }

    @Override
    public void updateDiscountPrice(Long productId, Integer discount) {
        List<Sku> skuList = skuMapper.selectList(new LambdaQueryWrapper<Sku>().eq(Sku::getProductId, productId));
        // 更新sku折扣价格
        if (!ObjectUtils.isEmpty(discount)) {
            skuList.forEach(item -> item.setDiscountPrice(item.getPrice().multiply(new BigDecimal(discount * 0.1))));
            skuList.forEach(item -> skuMapper.updateById(item));
        } else { // 折扣为空,将该商品的sku折扣价格同样设为空
            skuMapper.update(null, new LambdaUpdateWrapper<Sku>().eq(Sku::getProductId, productId).set(Sku::getDiscountPrice, null));
        }
    }
}
