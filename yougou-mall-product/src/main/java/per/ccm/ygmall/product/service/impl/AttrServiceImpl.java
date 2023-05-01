package per.ccm.ygmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.common.exception.YougouException;
import per.ccm.ygmall.common.response.ResponseCode;
import per.ccm.ygmall.common.service.BaseService;
import per.ccm.ygmall.common.util.ConvertUtils;
import per.ccm.ygmall.product.dto.AttrDTO;
import per.ccm.ygmall.product.entity.Attr;
import per.ccm.ygmall.product.mapper.AttrMapper;
import per.ccm.ygmall.product.service.AttrService;
import per.ccm.ygmall.product.service.AttrValueService;
import per.ccm.ygmall.product.vo.AttrVO;
import per.ccm.ygmall.product.vo.AttrValueVO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AttrServiceImpl extends BaseService implements AttrService {

    @Autowired
    private AttrMapper attrMapper;

    @Autowired
    private AttrValueService attrValueService;

    @Override
    public void save(AttrDTO attrDTO) {
        LambdaQueryWrapper<Attr> queryWrapper = new LambdaQueryWrapper<>();

        // 判断当前spu下是否存在该属性名称
        if (this.isExist(queryWrapper, attrDTO)) {
            throw new YougouException(ResponseCode.PRODUCT_ERROR_B2001);
        }
        // 一个spu最多拥有5个属性
        if (attrMapper.selectCount(queryWrapper.eq(Attr::getSpuId, attrDTO.getSpuId())) >= 5) {
            throw new YougouException(ResponseCode.PRODUCT_ERROR_B20002);
        }
        Attr attr = ConvertUtils.dtoConvertToEntity(attrDTO, Attr.class);
        attrMapper.insert(attr);
    }

    @Override
    public List<AttrVO> getAttrListBySpuId(Long spuId) throws Exception {
        LambdaQueryWrapper<Attr> queryWrapper = new LambdaQueryWrapper<>();

        List<AttrVO> attrList = ConvertUtils.converList(attrMapper.selectList(queryWrapper.eq(Attr::getSpuId, spuId)), AttrVO.class);
        // 获取商品属性ID列表
        List<Long> attrIdList = attrList.stream().map(AttrVO::getAttrId).collect(Collectors.toList());
        System.out.println(attrIdList);
        // 获取该商品属性的商品属性值
        List<AttrValueVO> attrValueList = attrValueService.getAttrValueListByAttrIdList(attrIdList);

        this.setAttrValueList(attrList, attrValueList);
        return attrList;
    }

    @Override
    public void update(AttrDTO attrDTO) {
        LambdaQueryWrapper<Attr> queryWrapper = new LambdaQueryWrapper<>();

        // 判断当前spu下是否存在该属性名称
        if (this.isExist(queryWrapper, attrDTO)) {
            throw new YougouException(ResponseCode.PRODUCT_ERROR_B2001);
        }
        Attr attr = ConvertUtils.dtoConvertToEntity(attrDTO, Attr.class);
        attrMapper.updateById(attr);
    }

    @Override
    public void batchDelete(List<Long> attrIdList) throws Exception {
        attrMapper.deleteBatchIds(attrIdList);
        // 删除对应的商品属性值
        attrValueService.batchDeleteByAttrIdList(attrIdList);
    }

    /**
     * 判断当前spu下是否存在该属性名称
     *
     * @param queryWrapper 查询
     * @param attrDTO 属性传输数据
     * @return 是否存在该属性名称
     * */
    private Boolean isExist(LambdaQueryWrapper<Attr> queryWrapper, AttrDTO attrDTO) {
        Attr attrExist = attrMapper.selectOne(queryWrapper.eq(Attr::getSpuId, attrDTO.getSpuId()).eq(Attr::getName, attrDTO.getName()));
        return !ObjectUtils.isEmpty(attrExist);
    }

    /**
     * 设置商品属性值列表
     *
     * @param attrList 商品属性列表
     * @param attrValueList 商品属性值列表
     * */
    private void setAttrValueList(List<AttrVO> attrList, List<AttrValueVO> attrValueList) {
        if (ObjectUtils.isEmpty(attrList) || ObjectUtils.isEmpty(attrValueList)) {
            return;
        }
        Map<Long, List<AttrValueVO>> map = attrValueList.stream().collect(Collectors.groupingBy(AttrValueVO::getAttrId));
        for (AttrVO attr : attrList) {
            attr.setAttrValueList(map.get(attr.getAttrId()));
        }
    }
}
