package per.ccm.ygmall.product.service.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.ccm.ygmall.common.exception.YougouException;
import per.ccm.ygmall.common.response.ResponseCode;
import per.ccm.ygmall.common.service.BaseService;
import per.ccm.ygmall.common.util.ConvertUtils;
import per.ccm.ygmall.product.dto.SkuDTO;
import per.ccm.ygmall.product.entity.QSku;
import per.ccm.ygmall.product.entity.Sku;
import per.ccm.ygmall.product.repository.SkuRepository;
import per.ccm.ygmall.product.service.SkuService;
import per.ccm.ygmall.product.vo.SkuVO;

import java.util.List;
import java.util.Optional;

@Service
public class SkuServiceImpl extends BaseService implements SkuService {

    @Autowired
    private SkuRepository skuRepository;

    @Override
    public void save(SkuDTO skuDTO) throws Exception {
        QSku qSku = QSku.sku;

        Optional<Sku> skuSpecsExist = skuRepository.findOne(qSku.specs.eq(skuDTO.getSpecs()));
        // 判断sku规格是否存在
        if (skuSpecsExist.isPresent()) {
            throw new YougouException(ResponseCode.PRODUCT_ERROR_B40001);
        }
        Sku sku = ConvertUtils.dtoConvertToEntity(skuDTO, Sku.class);
        skuRepository.save(sku);
    }

    @Override
    public List<SkuVO> getSkuListBySpuId(Long spuId) {
        QSku qSku = QSku.sku;
        QBean<SkuVO> qBean = this.getQBean(qSku);
        return super.jpaQueryFactory.select(qBean).from(qSku).where(qSku.spuId.eq(spuId)).fetch();
    }

    /**
     * 获取映射对象
     *
     * @param qSku querydsl实体
     * @return 映射对象
     * */
    private QBean<SkuVO> getQBean(QSku qSku) {
        return Projections.bean(SkuVO.class, qSku.skuId, qSku.spuId, qSku.skuStock, qSku.price, qSku.desc, qSku.specs);
    }
}
