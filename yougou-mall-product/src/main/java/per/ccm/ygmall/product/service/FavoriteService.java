package per.ccm.ygmall.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import per.ccm.ygmall.database.vo.PageVO;
import per.ccm.ygmall.product.dto.FavoriteDTO;
import per.ccm.ygmall.product.entity.Favorite;
import per.ccm.ygmall.product.vo.FavoriteVO;

public interface FavoriteService extends IService<Favorite> {
    /**
     * 保存收藏信息
     *
     * @param favoriteDTO 用户收藏传输数据
     * */
    void save(FavoriteDTO favoriteDTO) throws Exception;

    /**
     * 根据用户ID获取用户收藏分页信息
     *
     * @param userId 用户ID
     * @param page 分页信息
     * */
    PageVO<FavoriteVO> getFavoritePages(Long userId, Page<Favorite> page) throws Exception;

    /**
     * 该用户是否已经收藏该商品
     *
     * @param userId 用户ID
     * @param productId 商品ID
     * */
    Boolean isFavorite(Long userId, Long productId) throws Exception;
}
