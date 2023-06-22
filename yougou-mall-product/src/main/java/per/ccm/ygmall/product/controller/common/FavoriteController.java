package per.ccm.ygmall.product.controller.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.database.vo.PageVO;
import per.ccm.ygmall.product.dto.FavoriteDTO;
import per.ccm.ygmall.product.entity.Favorite;
import per.ccm.ygmall.product.service.FavoriteService;
import per.ccm.ygmall.product.vo.FavoriteVO;
import per.ccm.ygmall.security.util.SecurityContextUtils;

@RestController
@RequestMapping("/product/favorite")
@Tag(name = "收藏接口", description = "收藏接口")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody FavoriteDTO favoriteDTO) throws Exception {
        Long userId = SecurityContextUtils.getUserId();

        favoriteDTO.setUserId(userId);
        favoriteService.save(favoriteDTO);
        return ResponseEntity.success();
    }

    @GetMapping("/pages")
    @Operation(summary = "获取收藏分页信息", description = "获取收藏分页信息")
    @Parameters({
            @Parameter(name = "page_num", description = "当前页"),
            @Parameter(name = "page_size", description = "页数")})
    public ResponseEntity<PageVO<FavoriteVO>> getFavoritePages(
            @RequestParam(value = "page_num", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize) throws Exception {
        Long userId = SecurityContextUtils.getUserId();

        Page<Favorite> page = new Page<>(pageNum, pageSize);
        PageVO<FavoriteVO> pageVO = favoriteService.getFavoritePages(userId, page);
        return ResponseEntity.success(pageVO);
    }

    @GetMapping("/is_favorite")
    @Operation(summary = "是否已收藏该商品", description = "传入商品ID判断是否已收藏该商品")
    @Parameters(@Parameter(name = "product_d", description = "商品ID"))
    public ResponseEntity<Boolean> isFavorite(@RequestParam("product_id") Long productId) throws Exception {
        Long userId = SecurityContextUtils.getUserId();
        Boolean isFavorite = favoriteService.isFavorite(userId, productId);
        return ResponseEntity.success(isFavorite);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除用户收藏", description = "根据主键ID删除用户删除")
    @Parameter(name = "favorite_id", description = "收藏ID")
    public ResponseEntity<Void> delete(@RequestParam("favorite_id") Long favoriteId) throws Exception {
        favoriteService.delete(favoriteId);
        return ResponseEntity.success();
    }
}
