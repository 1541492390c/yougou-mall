package per.ccm.ygmall.platform.service.impl;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.common.exception.YougouException;
import per.ccm.ygmall.common.response.ResponseCode;
import per.ccm.ygmall.common.service.BaseService;
import per.ccm.ygmall.common.util.ConvertUtils;
import per.ccm.ygmall.common.vo.PageVO;
import per.ccm.ygmall.platform.dto.BannerDTO;
import per.ccm.ygmall.platform.entity.Banner;
import per.ccm.ygmall.platform.entity.QBanner;
import per.ccm.ygmall.platform.repository.BannerRepository;
import per.ccm.ygmall.platform.service.BannerService;
import per.ccm.ygmall.platform.vo.BannerVO;

import java.util.List;

@Service
public class BannerServiceImpl extends BaseService implements BannerService {

    @Autowired
    private BannerRepository bannerRepository;

    @Override
    public void save(BannerDTO bannerDTO) {
        QBanner qBanner = QBanner.banner;

        Predicate predicate = qBanner.page.eq(bannerDTO.getPage()).and(qBanner.type.eq(bannerDTO.getType()));
        //同一个平台的同一个页面最多只能有5个轮播图
        if (bannerRepository.count(predicate) >= 5) {
            throw new YougouException(ResponseCode.USER_ERROR_A00004);
        }
        Banner banner = ConvertUtils.dtoConvertToEntity(bannerDTO, Banner.class);
        bannerRepository.save(banner);
    }

    @Override
    public PageVO<BannerVO> getBannerPages(Integer type, Pageable pageable) {
        QBanner qBanner = QBanner.banner;
        QBean<BannerVO> qBean = this.getQBean(qBanner);
        Predicate predicate = qBanner.enabled.isTrue().and(qBanner.bannerId.isNotNull());

        if (ObjectUtils.isEmpty(type)) {
            predicate = ExpressionUtils.and(predicate, qBanner.type.eq(type));
        }
        Long total = bannerRepository.count();
        List<BannerVO> bannerList = super.jpaQueryFactory.select(qBean).from(qBanner)
                .where(predicate).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
        return new PageVO<>(total, bannerList);
    }

    @Override
    public List<BannerVO> getBannerList(Integer type, String page) {
        QBanner qBanner = QBanner.banner;
        QBean<BannerVO> qBean = this.getQBean(qBanner);
        return super.jpaQueryFactory.select(qBean).from(qBanner).where(qBanner.type.eq(type).and(qBanner.page.eq(page))).fetch();
    }

    /**
     * 获取映射对象
     *
     * @param qBanner querydsl实体
     * @return 映射对象
     * */
    private QBean<BannerVO> getQBean(QBanner qBanner) {
        return Projections.bean(BannerVO.class, qBanner.bannerId, qBanner.desc, qBanner.link, qBanner.img);
    }
}
