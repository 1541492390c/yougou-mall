package per.ccm.ygmall.product.service.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.ccm.ygmall.common.service.BaseService;
import per.ccm.ygmall.product.entity.QSku;
import per.ccm.ygmall.product.repository.SkuRepository;
import per.ccm.ygmall.product.service.SkuService;
import per.ccm.ygmall.product.vo.SkuVO;

import java.util.List;

@Service
public class SkuServiceImpl extends BaseService implements SkuService {

    @Autowired
    private SkuRepository skuRepository;

    @Override
    public List<SkuVO> getSkuList(Long spuId) {
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
