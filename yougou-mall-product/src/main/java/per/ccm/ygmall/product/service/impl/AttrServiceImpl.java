package per.ccm.ygmall.product.service.impl;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAUpdateClause;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.common.exception.YougouException;
import per.ccm.ygmall.common.response.ResponseCode;
import per.ccm.ygmall.common.service.BaseService;
import per.ccm.ygmall.common.util.ConvertUtils;
import per.ccm.ygmall.product.dto.AttrDTO;
import per.ccm.ygmall.product.entity.Attr;
import per.ccm.ygmall.product.entity.QAttr;
import per.ccm.ygmall.product.repository.AttrRepository;
import per.ccm.ygmall.product.service.AttrService;
import per.ccm.ygmall.product.service.AttrValueService;
import per.ccm.ygmall.product.vo.AttrVO;
import per.ccm.ygmall.product.vo.AttrValueVO;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttrServiceImpl extends BaseService implements AttrService {

    @Autowired
    private AttrRepository attrRepository;

    @Autowired
    private AttrValueService attrValueService;

    @Override
    public void save(AttrDTO attrDTO) {
        QAttr qAttr = QAttr.attr;

        Optional<Attr> attrExist = attrRepository.findOne(qAttr.spuId.eq(attrDTO.getSpuId()).and(qAttr.name.eq(attrDTO.getName())));
        // 判断当前spu下是否存在该属性名称
        if (attrExist.isPresent()) {
            throw new YougouException(ResponseCode.PRODUCT_ERROR_B2001);
        }
        // 一个spu最多拥有5个属性
        if (attrRepository.count(qAttr.spuId.eq(attrDTO.getSpuId())) >= 5) {
            throw new YougouException(ResponseCode.PRODUCT_ERROR_B20002);
        }

        Attr attr = ConvertUtils.dtoConvertToEntity(attrDTO, Attr.class);
        attrRepository.save(attr);
    }

    @Override
    public List<AttrVO> getAttrListBySpuId(Long spuId) throws Exception {
        QAttr qAttr = QAttr.attr;
        QBean<AttrVO> qBean = this.getQBean(qAttr);

        List<AttrVO> attrList = super.jpaQueryFactory.select(qBean).from(qAttr).where(qAttr.spuId.eq(spuId)).fetch();
        // 获取商品属性ID列表
        List<Long> attrIdList = attrList.stream().map(AttrVO::getAttrId).collect(Collectors.toList());
        // 获取该商品属性的商品属性值
        List<AttrValueVO> attrValueList = attrValueService.getAttrValueListByAttrIdList(attrIdList);

        this.setAttrValueList(attrList, attrValueList);
        return attrList;
    }

    @Override
    public void update(AttrDTO attrDTO) {
        QAttr qAttr = QAttr.attr;

        Predicate predicate = qAttr.spuId.eq(attrDTO.getSpuId()).and(qAttr.name.eq(attrDTO.getName()));
        Optional<Attr> attrExist = attrRepository.findOne(predicate);
        // 判断当前spu下是否存在该属性名称
        if (attrExist.isPresent()) {
            throw new YougouException(ResponseCode.PRODUCT_ERROR_B2001);
        }

        JPAUpdateClause jpaUpdateClause = super.jpaQueryFactory.update(qAttr);
        // 更新商品属性名称
        if (!ObjectUtils.isEmpty(attrDTO.getName())) {
            jpaUpdateClause.set(qAttr.name, attrDTO.getName());
        }
        jpaUpdateClause.where(qAttr.attrId.eq(attrDTO.getAttrId())).execute();
    }

    @Override
    public void batchDelete(List<Long> attrIdList) throws Exception {
        QAttr qAttr = QAttr.attr;

        super.jpaQueryFactory.delete(qAttr).where(qAttr.attrId.in(attrIdList)).execute();
        // 删除对应的商品属性值
        attrValueService.batchDeleteByAttrIdList(attrIdList);
    }

    /**
     * 获取映射对象
     *
     * @param qAttr querydsl实体
     * @return 映射对象
     * */
    private QBean<AttrVO> getQBean(QAttr qAttr) {
        return Projections.bean(AttrVO.class, qAttr.attrId, qAttr.spuId, qAttr.name);
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
