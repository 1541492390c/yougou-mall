package per.ccm.ygmall.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;
import per.ccm.ygmall.platform.entity.Banner;
import per.ccm.ygmall.platform.vo.BannerVO;

import java.util.List;

@Repository
public interface BannerMapper extends BaseMapper<Banner> {
    IPage<BannerVO> selectBannerPages(Integer type, Page<Banner> page);

    List<BannerVO> selectBannerList(Integer type, String page);
}
