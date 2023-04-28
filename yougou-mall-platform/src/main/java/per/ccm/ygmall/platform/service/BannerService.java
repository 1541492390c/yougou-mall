package per.ccm.ygmall.platform.service;

import org.springframework.data.domain.Pageable;
import per.ccm.ygmall.common.vo.PageVO;
import per.ccm.ygmall.platform.dto.BannerDTO;
import per.ccm.ygmall.platform.vo.BannerVO;

import java.util.List;

public interface BannerService {
    /**
     * 保存轮播图信息
     *
     * @param bannerDTO 轮播图传输数据
     * */
    void save(BannerDTO bannerDTO) throws Exception;

    /**
     * 分页获取轮播图信息
     *
     * @param type 轮播图类型
     * @param pageable 分页信息
     * @return 轮播图分页信息
     * */
    PageVO<BannerVO> getBannerPages(Integer type, Pageable pageable) throws Exception;

    /**
     * 获取轮播图信息列表
     *
     * @param type 轮播图类型
     * @param page 所属页面
     * @return 轮播图列表
     * */
    List<BannerVO> getBannerList(Integer type, String page) throws Exception;
}
