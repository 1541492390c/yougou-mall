package per.ccm.ygmall.product.controller.common;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import per.ccm.ygmall.common.controller.BaseController;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.common.vo.PageVO;
import per.ccm.ygmall.product.service.SpuService;
import per.ccm.ygmall.product.vo.SpuVO;

import java.util.List;

@RestController
@RequestMapping("/product/spu")
@Tag(name = "spu接口", description = "spu接口")
public class SpuController extends BaseController {

    @Autowired
    private SpuService spuService;

    @GetMapping("/recommended_list")
    public ResponseEntity<List<SpuVO>> getRecommendedList() throws Exception {
        List<SpuVO> recommendedList = spuService.getRecommendedSpuList();
        return ResponseEntity.success(recommendedList);
    }

    @GetMapping("/pages")
    @Operation(summary = "根据分类路径分页获取商品信息列表", description = "根据分类路径分页获取商品信息列表")
    @Parameters({
            @Parameter(name = "pageNum", description = "当前页"),
            @Parameter(name = "pageSize", description = "页数"),
            @Parameter(name = "categories", description = "分类路径")})
    public ResponseEntity<PageVO<SpuVO>> getByCategories(
            @RequestParam(value = "page_num", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "categories") String categories) throws Exception {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        PageVO<SpuVO> pageVO = spuService.getSpuPages(categories, pageable);
        return ResponseEntity.success(pageVO);
    }
}
