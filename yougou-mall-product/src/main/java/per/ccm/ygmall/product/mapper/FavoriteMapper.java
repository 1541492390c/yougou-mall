package per.ccm.ygmall.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;
import per.ccm.ygmall.product.entity.Favorite;
import per.ccm.ygmall.product.vo.FavoriteVO;

@Repository
public interface FavoriteMapper extends BaseMapper<Favorite> {
    Page<FavoriteVO> selectFavoritePages(Long userId, Page<Favorite> page);
}
