package per.ccm.ygmall.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.api.product.bo.SkuBO;
import per.ccm.ygmall.api.product.bo.SkuSpecsBO;
import per.ccm.ygmall.common.exception.YougouException;
import per.ccm.ygmall.common.response.ResponseCodeEnum;
import per.ccm.ygmall.common.util.ConvertUtils;
import per.ccm.ygmall.product.dto.SkuDTO;
import per.ccm.ygmall.product.entity.Sku;
import per.ccm.ygmall.product.mapper.SkuMapper;
import per.ccm.ygmall.product.service.SkuService;
import per.ccm.ygmall.product.service.SkuSpecsService;
import per.ccm.ygmall.product.vo.SkuVO;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private SkuSpecsService skuSpecsService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SkuDTO skuDTO) throws Exception {
        if (ObjectUtils.isEmpty(skuDTO.getSkuSpecs())) {
            return;
        }
        // 判断sku规格是否存在
        if (skuSpecsService.isExist(skuDTO.getProductId(), skuDTO.getSkuSpecs())) {
            throw new YougouException(ResponseCodeEnum.PRODUCT_ERROR_B4001);
        }
        Sku sku = ConvertUtils.convertProperties(skuDTO, Sku.class);
        skuMapper.insert(sku);

        skuDTO.getSkuSpecs().forEach(item -> item.setSkuId(sku.getSkuId()));
        // 批量保存sku规格
        skuSpecsService.batchSave(skuDTO.getSkuSpecs());
    }

    @Override
    public List<SkuVO> getSkuListByProductId(Long productId) {
        return skuMapper.selectSkuList(productId);
    }

    @Override
    public List<SkuBO> getSkuBOList(List<Long> skuIdList) {
        List<SkuVO> skuList = skuMapper.selectSkuListBySkuIdList(skuIdList);

        List<SkuBO> skuBOList = new ArrayList<>();
        for (SkuVO skuVO : skuList) {
            // 设置sku内部传输数据
            SkuBO skuBO = new SkuBO();
            skuBO.setSkuId(skuVO.getSkuId());
            skuBO.setProductId(skuVO.getProductId());
            skuBO.setPrice(skuVO.getPrice());
            // 设置sku规格列表
            List<SkuSpecsBO> skuSpecsBOList = ConvertUtils.converList(skuVO.getSkuSpecs(), SkuSpecsBO.class);
            skuBO.setSkuSpecsBOList(skuSpecsBOList);
            skuBOList.add(skuBO);
        }
        return skuBOList;
    }
}
