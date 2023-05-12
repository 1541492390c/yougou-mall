package per.ccm.ygmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.common.exception.YougouException;
import per.ccm.ygmall.common.response.ResponseCode;
import per.ccm.ygmall.common.util.ConvertUtils;
import per.ccm.ygmall.product.dto.AttrValueDTO;
import per.ccm.ygmall.product.entity.AttrValue;
import per.ccm.ygmall.product.mapper.AttrValueMapper;
import per.ccm.ygmall.product.service.AttrValueService;
import per.ccm.ygmall.product.vo.AttrValueVO;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AttrValueServiceImpl implements AttrValueService {

    @Autowired
    private AttrValueMapper attrValueMapper;

    @Override
    public void batchSave(List<AttrValueDTO> attrValueDTOList) {
        LambdaQueryWrapper<AttrValue> queryWrapper = new LambdaQueryWrapper<>();

        List<AttrValue> attrValueList = ConvertUtils.converList(attrValueDTOList, AttrValue.class);
        if (ObjectUtils.isEmpty(attrValueList)) {
            return;
        }
        // 判断传输的属性值名称是否重复
        Set<String> attrValueDTORepeatSet = attrValueList.stream().map(AttrValue::getName).collect(Collectors.toSet());
        if (attrValueDTORepeatSet.size() < attrValueDTOList.size()) {
            throw new YougouException(ResponseCode.PRODUCT_ERROR_B30001);
        }

        List<Long> attrIdList = attrValueList.stream().distinct().map(AttrValue::getAttrId).collect(Collectors.toList());
        List<AttrValue> attrValueExistList = attrValueMapper.selectList(queryWrapper.in(AttrValue::getAttrId, attrIdList));
        // 判断商品属性值是否存在或过多
        this.isExistOrTooMuch(attrValueExistList, attrValueList);
        attrValueList.forEach(item -> attrValueMapper.insert(item));
    }

    @Override
    public List<AttrValueVO> getAttrValueListByAttrValueIdList(List<Long> attrValueIdList) {
        LambdaQueryWrapper<AttrValue> queryWrapper = new LambdaQueryWrapper<>();
        List<AttrValue> attrValueList = attrValueMapper.selectList(queryWrapper.in(AttrValue::getAttrValueId, attrValueIdList));
        return ConvertUtils.converList(attrValueList, AttrValueVO.class);
    }

    @Override
    public void batchDelete(List<Long> attrValueIdList) {
        attrValueMapper.deleteBatchIds(attrValueIdList);
    }

    @Override
    public void batchDeleteByAttrIdList(List<Long> attrIdList) {
        LambdaQueryWrapper<AttrValue> queryWrapper = new LambdaQueryWrapper<>();
        attrValueMapper.delete(queryWrapper.in(AttrValue::getAttrId, attrIdList));
    }

    /**
     * 判断商品属性值是否存在或过多(直接抛异常)
     *
     * @param attrValueExistList 已存在属性值列表
     * @param attrValueList 新增属性值列表
     * */
    private void isExistOrTooMuch(List<AttrValue> attrValueExistList, List<AttrValue> attrValueList) {
        if (ObjectUtils.isEmpty(attrValueExistList) || ObjectUtils.isEmpty(attrValueList)) {
            return;
        }
        List<AttrValue> attrValueMergerList = new ArrayList<>(attrValueExistList);
        attrValueMergerList.addAll(attrValueList);

        Map<Long, List<AttrValue>> map = attrValueMergerList.stream().collect(Collectors.groupingBy(AttrValue::getAttrId));
        for (List<AttrValue> values : map.values()) {
            if (values.size() >= 10) {
                throw new YougouException(ResponseCode.PRODUCT_ERROR_B30003);
            }
            Set<String> valueSet = values.stream().map(AttrValue::getName).collect(Collectors.toSet());
            if (valueSet.size() < values.size()) {
                throw new YougouException(ResponseCode.PRODUCT_ERROR_B30002);
            }
        }
    }
}
