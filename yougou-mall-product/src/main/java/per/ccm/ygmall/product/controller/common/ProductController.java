package per.ccm.ygmall.product.controller.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.database.vo.PageVO;
import per.ccm.ygmall.product.entity.Product;
import per.ccm.ygmall.product.service.ProductService;
import per.ccm.ygmall.product.vo.ProductVO;

import java.util.List;

@RestController
@RequestMapping("/product")
@Tag(name = "商品接口", description = "商品接口")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/recommended_list")
    public ResponseEntity<List<ProductVO>> getRecommendedList() throws Exception {
        List<ProductVO> recommendedList = productService.getRecommendedProductList();
        return ResponseEntity.success(recommendedList);
    }

    @GetMapping("/pages")
    @Operation(summary = "根据分类路径分页获取商品信息列表", description = "根据分类路径分页获取商品信息列表")
    @Parameters({
            @Parameter(name = "pageNum", description = "当前页"),
            @Parameter(name = "pageSize", description = "页数"),
            @Parameter(name = "categories", description = "分类路径")})
    public ResponseEntity<PageVO<ProductVO>> getByCategories(
            @RequestParam(value = "page_num", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "categories", required = false) String categories) throws Exception {
        Page<Product> page = new Page<>(pageNum, pageSize);
        PageVO<ProductVO> pageVO = productService.getProductPages(categories, page);
        return ResponseEntity.success(pageVO);
    }
}
