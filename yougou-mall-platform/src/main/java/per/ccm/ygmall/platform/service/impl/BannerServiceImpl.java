package per.ccm.ygmall.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.common.exception.YougouException;
import per.ccm.ygmall.common.response.ResponseCodeEnum;
import per.ccm.ygmall.common.util.ConvertUtils;
import per.ccm.ygmall.database.vo.PageVO;
import per.ccm.ygmall.platform.dto.BannerDTO;
import per.ccm.ygmall.platform.entity.Banner;
import per.ccm.ygmall.platform.mapper.BannerMapper;
import per.ccm.ygmall.platform.service.BannerService;
import per.ccm.ygmall.platform.vo.BannerVO;

import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public void save(BannerDTO bannerDTO) {
        LambdaQueryWrapper<Banner> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(Banner::getPage, bannerDTO.getPage()).eq(Banner::getType, bannerDTO.getType());
        //同一个平台的同一个页面最多只能有5个轮播图
        if (bannerMapper.selectCount(queryWrapper) >= 5) {
            throw new YougouException(ResponseCodeEnum.USER_ERROR_A00004);
        }
        Banner banner = ConvertUtils.convertProperties(bannerDTO, Banner.class);
        bannerMapper.insert(banner);
    }

    @Override
    public PageVO<BannerVO> getBannerPages(Integer type, Page<Banner> page) {
        LambdaQueryWrapper<Banner> queryWrapper = new LambdaQueryWrapper<>();

        if (!ObjectUtils.isEmpty(type)) {
            queryWrapper.eq(Banner::getType, type);
        }
        IPage<Banner> pageInfo = bannerMapper.selectPage(page, queryWrapper);
        List<BannerVO> bannerList = ConvertUtils.converList(pageInfo.getRecords(), BannerVO.class);
        return new PageVO<>(pageInfo.getTotal(), bannerList);
    }

    @Override
    public List<BannerVO> getBannerList(Integer type, String page) {
        LambdaQueryWrapper<Banner> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(Banner::getType, type).eq(Banner::getPage, page);
        List<Banner> bannerList = bannerMapper.selectList(queryWrapper);
        return ConvertUtils.converList(bannerList, BannerVO.class);
    }
}
