package per.ccm.ygmall.product.service.impl;

import com.querydsl.core.types.*;
import com.querydsl.jpa.impl.JPAUpdateClause;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.common.exception.YougouException;
import per.ccm.ygmall.common.response.ResponseCode;
import per.ccm.ygmall.common.service.BaseService;
import per.ccm.ygmall.common.util.ConvertUtils;
import per.ccm.ygmall.common.vo.PageVO;
import per.ccm.ygmall.product.dto.SpuDTO;
import per.ccm.ygmall.product.entity.QSku;
import per.ccm.ygmall.product.entity.QSpu;
import per.ccm.ygmall.product.entity.Spu;
import per.ccm.ygmall.product.repository.SpuRepository;
import per.ccm.ygmall.product.service.SpuService;
import per.ccm.ygmall.product.vo.SpuVO;

import java.util.List;
import java.util.Optional;

@Service
public class SpuServiceImpl extends BaseService implements SpuService {

    @Autowired
    private SpuRepository spuRepository;

    @Override
    public void save(SpuDTO spuDTO) {
        QSpu qSpu = QSpu.spu;

        Optional<Spu> spuExist = spuRepository.findOne(qSpu.name.eq(spuDTO.getName()));
        // 判断商品名称是否存在
        if (spuExist.isPresent()) {
            throw new YougouException(ResponseCode.PRODUCT_ERROR_B10001);
        }
        Spu spu = ConvertUtils.dtoConvertToEntity(spuDTO, Spu.class);
        spuRepository.save(spu);
    }

    @Override
    public List<SpuVO> getRecommendedSpuList() {
        QSpu qSpu = QSpu.spu;
        QSku qSku = QSku.sku;
        return null;
    }

    @Override
    public PageVO<SpuVO> getSpuPages(Pageable pageable) {
        QSpu qSpu = QSpu.spu;
        QSku qSku = QSku.sku;
        QBean<SpuVO> qBean = this.getQBean(qSpu, qSku);

        Long total = spuRepository.count();
        List<SpuVO> spuList = super.jpaQueryFactory.select(qBean)
                .from(qSpu)
                .leftJoin(qSku)
                .on(qSpu.spuId.eq(qSpu.spuId))
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .distinct()
                .fetch();
        return new PageVO<>(total, spuList);
    }

    @Override
    public void update(SpuDTO spuDTO) {
        QSpu qSpu = QSpu.spu;

        Optional<Spu> spuExist = spuRepository.findOne(qSpu.name.eq(spuDTO.getName()));
        // 判断商品名称是否存在
        if (spuExist.isPresent()) {
            throw new YougouException(ResponseCode.PRODUCT_ERROR_B10001);
        }

        JPAUpdateClause jpaUpdateClause = super.jpaQueryFactory.update(qSpu);
        // 更新商品spu名称
        if (!ObjectUtils.isEmpty(spuDTO.getName())) {
            jpaUpdateClause.set(qSpu.name, spuDTO.getName());
        }
        // 更新spu分类
        if (!ObjectUtils.isEmpty(spuDTO.getCategories())) {
            jpaUpdateClause.set(qSpu.categories, spuDTO.getCategories());
        }
        // 更新spu封面
        if (!ObjectUtils.isEmpty(spuDTO.getCover())) {
            jpaUpdateClause.set(qSpu.cover, spuDTO.getCover());
        }
        // 更新spu状态
        if (!ObjectUtils.isEmpty(spuDTO.getState())) {
            jpaUpdateClause.set(qSpu.state, spuDTO.getState());
        }
        jpaUpdateClause.where(qSpu.spuId.eq(spuDTO.getSpuId())).execute();
    }

    /**
     * 获取映射对象
     *
     * @param qSpu querydsl实体
     * @param qSku querydsl实体
     * @return 映射对象
     * */
    private QBean<SpuVO> getQBean(QSpu qSpu, QSku qSku) {
        // sku最低价格
        Expression<Double> expression = super.jpaQueryFactory.select(qSku.price.min()).from(qSku).where(qSpu.spuId.eq(qSku.spuId));
        return Projections.bean(SpuVO.class, qSpu.spuId, qSpu.brandId, qSpu.name, qSpu.state, qSpu.categories, qSpu.cover,
                ExpressionUtils.as(expression, "price"));
    }
}
