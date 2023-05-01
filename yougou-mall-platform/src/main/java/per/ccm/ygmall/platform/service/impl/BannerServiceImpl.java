package per.ccm.ygmall.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.ccm.ygmall.common.exception.YougouException;
import per.ccm.ygmall.common.response.ResponseCode;
import per.ccm.ygmall.common.service.BaseService;
import per.ccm.ygmall.common.util.ConvertUtils;
import per.ccm.ygmall.common.vo.PageVO;
import per.ccm.ygmall.platform.dto.BannerDTO;
import per.ccm.ygmall.platform.entity.Banner;
import per.ccm.ygmall.platform.mapper.BannerMapper;
import per.ccm.ygmall.platform.service.BannerService;
import per.ccm.ygmall.platform.vo.BannerVO;

import java.util.List;

@Service
public class BannerServiceImpl extends BaseService implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public void save(BannerDTO bannerDTO) {
        LambdaQueryWrapper<Banner> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(Banner::getPage, bannerDTO.getPage()).eq(Banner::getType, bannerDTO.getType());
        //同一个平台的同一个页面最多只能有5个轮播图
        if (bannerMapper.selectCount(queryWrapper) >= 5) {
            throw new YougouException(ResponseCode.USER_ERROR_A00004);
        }
        Banner banner = ConvertUtils.dtoConvertToEntity(bannerDTO, Banner.class);
        bannerMapper.insert(banner);
    }

    @Override
    public PageVO<BannerVO> getBannerPages(Integer type, Page<Banner> page) {
        IPage<BannerVO> pageInfo = bannerMapper.selectBannerPages(type, page);
        return new PageVO<>(pageInfo.getTotal(), pageInfo.getRecords());
    }

    @Override
    public List<BannerVO> getBannerList(Integer type, String page) {
        return bannerMapper.selectBannerList(type, page);
    }
}
