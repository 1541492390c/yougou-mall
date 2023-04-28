package per.ccm.ygmall.product.service.impl;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.common.exception.YougouException;
import per.ccm.ygmall.common.response.ResponseCode;
import per.ccm.ygmall.common.service.BaseService;
import per.ccm.ygmall.common.util.ConvertUtils;
import per.ccm.ygmall.product.dto.AttrValueDTO;
import per.ccm.ygmall.product.entity.AttrValue;
import per.ccm.ygmall.product.entity.QAttrValue;
import per.ccm.ygmall.product.repository.AttrValueRepository;
import per.ccm.ygmall.product.service.AttrValueService;
import per.ccm.ygmall.product.vo.AttrValueVO;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AttrValueServiceImpl extends BaseService implements AttrValueService {

    @Autowired
    private AttrValueRepository attrValueRepository;

    @Override
    public void batchSave(List<AttrValueDTO> attrValueDTOList) {
        QAttrValue qAttrValue = QAttrValue.attrValue;

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
        List<AttrValue> attrValueExistList = super.jpaQueryFactory.selectFrom(qAttrValue).where(qAttrValue.attrId.in(attrIdList)).fetch();

        // 判断商品属性值是否存在或过多
        this.isRepeatOrTooMuch(attrValueExistList, attrValueList);
        attrValueRepository.saveAll(attrValueList);
    }

    @Override
    public List<AttrValueVO> getAttrValueListByAttrIdList(List<Long> attrIdList) {
        QAttrValue qAttrValue = QAttrValue.attrValue;
        QBean<AttrValueVO> qBean = this.getQBean(qAttrValue);
        return super.jpaQueryFactory.select(qBean).from(qAttrValue).where(qAttrValue.attrId.in(attrIdList)).fetch();
    }

    @Override
    public void update(AttrValueDTO attrValueDTO) throws Exception {
        QAttrValue qAttrValue = QAttrValue.attrValue;
    }

    @Override
    public void batchDelete(List<Long> attrValueIdList) {
        QAttrValue qAttrValue = QAttrValue.attrValue;
        super.jpaQueryFactory.delete(qAttrValue).where(qAttrValue.attrValueId.in(attrValueIdList)).execute();
    }

    @Override
    public void batchDeleteByAttrIdList(List<Long> attrIdList) {
        QAttrValue qAttrValue = QAttrValue.attrValue;
        Predicate predicate = qAttrValue.attrId.in(attrIdList);
        super.jpaQueryFactory.delete(qAttrValue).where(predicate).execute();
    }

    /**
     * 获取映射对象
     *
     * @param qAttrValue querydsl实体
     * @return 映射对象
     * */
    private QBean<AttrValueVO> getQBean(QAttrValue qAttrValue) {
        return Projections.bean(AttrValueVO.class, qAttrValue.attrId, qAttrValue.attrValueId, qAttrValue.name);
    }

    /**
     * 判断商品属性值是否存在或过多(直接抛异常)
     *
     * @param attrValueExistList 已存在属性值列表
     * @param attrValueList 新增属性值列表
     * */
    private void isRepeatOrTooMuch(List<AttrValue> attrValueExistList, List<AttrValue> attrValueList) {
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
