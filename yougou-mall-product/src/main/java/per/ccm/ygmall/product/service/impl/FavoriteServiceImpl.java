package per.ccm.ygmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.ccm.ygmall.common.exception.YougouException;
import per.ccm.ygmall.common.response.ResponseCodeEnum;
import per.ccm.ygmall.common.util.ConvertUtils;
import per.ccm.ygmall.database.vo.PageVO;
import per.ccm.ygmall.product.dto.FavoriteDTO;
import per.ccm.ygmall.product.entity.Favorite;
import per.ccm.ygmall.product.mapper.FavoriteMapper;
import per.ccm.ygmall.product.service.FavoriteService;
import per.ccm.ygmall.product.vo.FavoriteVO;

@Service
public class FavoriteServiceImpl extends ServiceImpl<FavoriteMapper, Favorite> implements FavoriteService {

    @Autowired
    private FavoriteMapper favoriteMapper;

    @Override
    public void save(FavoriteDTO favoriteDTO) throws Exception {
        LambdaQueryWrapper<Favorite> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(Favorite::getUserId, favoriteDTO.getUserId()).eq(Favorite::getProductId, favoriteDTO.getProductId());
        // 用户已收藏该商品
        if (favoriteMapper.exists(queryWrapper)) {
            throw new YougouException(ResponseCodeEnum.PRODUCT_ERROR_C5001);
        }
        Favorite favorite = ConvertUtils.convertProperties(favoriteDTO, Favorite.class);
        favoriteMapper.insert(favorite);
    }

    @Override
    public PageVO<FavoriteVO> getFavoritePages(Long userId, Page<Favorite> page) {
        Page<FavoriteVO> pageInfo = favoriteMapper.selectFavoritePages(userId, page);
        return new PageVO<>(pageInfo.getTotal(), pageInfo.getRecords());
    }

    @Override
    public Boolean isFavorite(Long userId, Long productId) {
        LambdaQueryWrapper<Favorite> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Favorite::getUserId, userId).eq(Favorite::getProductId, productId);
        return favoriteMapper.exists(queryWrapper);
    }
}
