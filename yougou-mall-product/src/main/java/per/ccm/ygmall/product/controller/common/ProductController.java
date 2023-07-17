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
    @Operation(summary = "获取推荐商品列表", description = "获取推荐商品列表")
    public ResponseEntity<List<ProductVO>> getRecommendedList() throws Exception {
        List<ProductVO> recommendedList = productService.getRecommendedProductList();
        return ResponseEntity.success(recommendedList);
    }

    @GetMapping("/pages")
    @Operation(summary = "根据分类路径分页获取商品信息列表", description = "根据分类路径分页获取商品信息列表")
    @Parameters({
            @Parameter(name = "page_num", description = "当前页"),
            @Parameter(name = "page_size", description = "页数"),
            @Parameter(name = "category_node", description = "分类节点")})
    public ResponseEntity<PageVO<ProductVO>> getByCategories(
            @RequestParam(value = "page_num", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "category_node", required = false) String categoryNode) throws Exception {
        Page<Product> page = new Page<>(pageNum, pageSize);
        PageVO<ProductVO> pageVO = productService.getProductPages(categoryNode, page);
        return ResponseEntity.success(pageVO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取商品详情信息", description = "传入商品ID获取商品详情信息")
    public ResponseEntity<ProductVO> getProductByProductId(@PathVariable("id") Long productId) throws Exception {
        ProductVO productVO = productService.getProductByProductId(productId);
        return ResponseEntity.success(productVO);
    }
}
