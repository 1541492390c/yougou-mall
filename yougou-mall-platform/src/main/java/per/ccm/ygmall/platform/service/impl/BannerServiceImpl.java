package per.ccm.ygmall.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.common.cache.cache.CacheNames;
import per.ccm.ygmall.common.basic.exception.YougouException;
import per.ccm.ygmall.common.basic.response.ResponseCodeEnum;
import per.ccm.ygmall.common.basic.util.ConvertUtils;
import per.ccm.ygmall.common.basic.vo.PageVO;
import per.ccm.ygmall.platform.dto.BannerDTO;
import per.ccm.ygmall.platform.entity.Banner;
import per.ccm.ygmall.platform.mapper.BannerMapper;
import per.ccm.ygmall.platform.service.BannerService;
import per.ccm.ygmall.platform.vo.BannerVO;

import java.util.List;

@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    @CacheEvict(cacheNames = CacheNames.BANNER_CACHE_NAME, key = "#bannerDTO.type + ':' + #bannerDTO.page")
    public void save(BannerDTO bannerDTO) {
        LambdaQueryWrapper<Banner> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(Banner::getPage, bannerDTO.getPage()).eq(Banner::getType, bannerDTO.getType());
        //同一个平台的同一个页面最多只能有5个轮播图
        if (bannerMapper.selectCount(queryWrapper) >= 5) {
            throw new YougouException(ResponseCodeEnum.PLATFORM_ERROR_F0001);
        }
        Banner banner = ConvertUtils.convertProperties(bannerDTO, Banner.class);
        bannerMapper.insert(banner);
    }

    @Override
    public PageVO<BannerVO> getBannerPages(Integer type, String page, String link, Page<Banner> bPage) {
        LambdaQueryWrapper<Banner> queryWrapper = new LambdaQueryWrapper<>();

        // 类型不为空
        if (!ObjectUtils.isEmpty(type)) {
            queryWrapper.eq(Banner::getType, type);
        }
        // 所属页面不为空
        if (!ObjectUtils.isEmpty(page)) {
            queryWrapper.eq(Banner::getPage, page);
        }
        // 轮播图链接不为空
        if (!ObjectUtils.isEmpty(link)) {
            queryWrapper.eq(Banner::getLink, link);
        }
        Page<Banner> pageInfo = bannerMapper.selectPage(bPage, queryWrapper);
        List<BannerVO> bannerList = ConvertUtils.converList(pageInfo.getRecords(), BannerVO.class);
        return new PageVO<>(pageInfo.getTotal(), bannerList);
    }

    @Override
    @Cacheable(value = CacheNames.BANNER_CACHE_NAME, key = "#type + ':' + #page", sync = true)
    public List<BannerVO> getBannerList(Integer type, String page) {
        LambdaQueryWrapper<Banner> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(Banner::getType, type).eq(Banner::getPage, page);
        List<Banner> bannerList = bannerMapper.selectList(queryWrapper);
        return ConvertUtils.converList(bannerList, BannerVO.class);
    }

    @Override
    public void update(BannerDTO bannerDTO) {
        Banner banner = ConvertUtils.convertProperties(bannerDTO, Banner.class);
        bannerMapper.updateById(banner);
    }
}
