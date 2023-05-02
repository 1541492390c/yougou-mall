package per.ccm.ygmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.common.service.BaseService;
import per.ccm.ygmall.common.util.ConvertUtils;
import per.ccm.ygmall.product.dto.SkuSpecsDTO;
import per.ccm.ygmall.product.entity.SkuSpecs;
import per.ccm.ygmall.product.mapper.SkuSpecsMapper;
import per.ccm.ygmall.product.service.AttrService;
import per.ccm.ygmall.product.service.AttrValueService;
import per.ccm.ygmall.product.service.SkuSpecsService;
import per.ccm.ygmall.product.vo.AttrVO;
import per.ccm.ygmall.product.vo.AttrValueVO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkuSpecsServiceImpl extends BaseService implements SkuSpecsService {

    @Autowired
    private SkuSpecsMapper skuSpecsMapper;

    @Autowired
    private AttrService attrService;

    @Autowired
    private AttrValueService attrValueService;

    @Override
    public Boolean isExist(List<SkuSpecsDTO> skuSpecsDTOList) {
        LambdaQueryWrapper<SkuSpecs> queryWrapper = new LambdaQueryWrapper<>();

        List<SkuSpecs> skuSpecsList = ConvertUtils.converList(skuSpecsDTOList, SkuSpecs.class);
        for (SkuSpecs skuSpecs : skuSpecsList) {
            queryWrapper.eq(SkuSpecs::getSpuId, skuSpecs.getSpuId())
                    .eq(SkuSpecs::getAttrId, skuSpecs.getAttrId())
                    .eq(SkuSpecs::getAttrValueId, skuSpecs.getAttrValueId());
            if (skuSpecsMapper.exists(queryWrapper)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void batchSave(List<SkuSpecsDTO> skuSpecsDTOList) throws Exception {
        List<SkuSpecs> skuSpecsList = ConvertUtils.converList(skuSpecsDTOList, SkuSpecs.class);

        List<Long> attrIdList = skuSpecsList.stream().distinct().map(SkuSpecs::getAttrId).collect(Collectors.toList());
        List<AttrVO> attrList = attrService.getAttrListByAttrIdList(attrIdList);
        // 设置属性名称
        for (AttrVO attr : attrList) {
            skuSpecsList.forEach(item -> {
                if (ObjectUtils.nullSafeEquals(attr.getAttrId(), item.getAttrId())) {
                    item.setAttrName(attr.getName());
                }
            });
        }

        List<Long> attrValueIdList = skuSpecsList.stream().distinct().map(SkuSpecs::getAttrValueId).collect(Collectors.toList());
        List<AttrValueVO> attrValueList = attrValueService.getAttrValueListByAttrValueIdList(attrValueIdList);
        // 设置属性值名称
        for (AttrValueVO attrValue : attrValueList) {
            skuSpecsList.forEach(item -> {
                if (ObjectUtils.nullSafeEquals(attrValue.getAttrValueId(), item.getAttrValueId())) {
                    item.setAttrValueName(attrValue.getName());
                }
            });
        }
        skuSpecsList.forEach(item -> skuSpecsMapper.insert(item));
    }
}
