package per.ccm.ygmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.common.basic.exception.YougouException;
import per.ccm.ygmall.common.basic.response.ResponseCodeEnum;
import per.ccm.ygmall.common.basic.util.ConvertUtils;
import per.ccm.ygmall.common.cache.cache.CacheNames;
import per.ccm.ygmall.product.dto.AttrDTO;
import per.ccm.ygmall.product.dto.AttrValueDTO;
import per.ccm.ygmall.product.entity.Attr;
import per.ccm.ygmall.product.mapper.AttrMapper;
import per.ccm.ygmall.product.service.AttrService;
import per.ccm.ygmall.product.service.AttrValueService;
import per.ccm.ygmall.product.vo.AttrVO;

import java.util.List;

@Service
public class AttrServiceImpl extends ServiceImpl<AttrMapper, Attr> implements AttrService {

    @Autowired
    private AttrMapper attrMapper;

    @Autowired
    private AttrValueService attrValueService;

    @Override
    public void batchSave(List<AttrDTO> attrDTOList) throws Exception {
        LambdaQueryWrapper<Attr> queryWrapper = new LambdaQueryWrapper<>();

        for (AttrDTO attrDTO : attrDTOList) {
            // 判断当前商品下是否存在该属性名称
            if (this.isExist(attrDTO)) {
                throw new YougouException(ResponseCodeEnum.PRODUCT_ERROR_C2001);
            }
            // 一个商品最多拥有5个属性
            if (attrMapper.selectCount(queryWrapper.eq(Attr::getProductId, attrDTO.getProductId())) >= 5) {
                throw new YougouException(ResponseCodeEnum.PRODUCT_ERROR_C2002);
            }
            // 将属性DTO转为实体
            Attr attr = ConvertUtils.convertProperties(attrDTO, Attr.class);
            attrMapper.insert(attr);
            // 写入属性的主键ID
            List<AttrValueDTO> attrValueDTOList = attrDTO.getAttrValueList();
            attrValueDTOList.forEach(item -> item.setAttrId(attr.getAttrId()));
            // 批量保存属性值
            attrValueService.batchSave(attrValueDTOList);
        }
    }

    @Override
    @Cacheable(cacheNames = CacheNames.PRODUCT_ATTR_CACHE_NAME, key = "#productId", sync = true)
    public List<AttrVO> getAttrListByProductId(Long productId) {
        return attrMapper.selectAttrList(productId);
    }

    @Override
    public void update(AttrDTO attrDTO) {
        // 判断当前商品下是否存在该属性名称
        if (this.isExist(attrDTO)) {
            throw new YougouException(ResponseCodeEnum.PRODUCT_ERROR_C2001);
        }
        Attr attr = ConvertUtils.convertProperties(attrDTO, Attr.class);
        attrMapper.updateById(attr);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchDelete(List<Long> attrIdList) throws Exception {
        attrMapper.deleteBatchIds(attrIdList);
        // 删除对应的商品属性值
        attrValueService.batchDeleteByAttrIdList(attrIdList);
    }

    /**
     * 判断当前spu下是否存在该属性名称
     *
     * @param attrDTO 属性传输数据
     * @return 是否存在该属性名称
     * */
    private Boolean isExist(AttrDTO attrDTO) {
        LambdaQueryWrapper<Attr> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Attr::getProductId, attrDTO.getProductId()).eq(Attr::getName, attrDTO.getName());
        Attr attrExist = attrMapper.selectOne(queryWrapper);
        return !ObjectUtils.isEmpty(attrExist);
    }
}
