package per.ccm.ygmall.product.controller.common;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.product.service.CategoryService;
import per.ccm.ygmall.product.vo.CategoryVO;

import java.util.List;

@RestController
@RequestMapping("/product/category")
@Tag(name = "分类接口", description = "分类接口")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    @Operation(summary = "获取分类信息列表", description = "传入分类ID和分类级别可进行条件查询")
    @Parameter(name = "parent_id", description = "父级ID")
    public ResponseEntity<List<CategoryVO>> getCategoryList(
            @RequestParam(value = "parent_id", defaultValue = "0") Long parentId) throws Exception {
        List<CategoryVO> categoryList = categoryService.getCategoryList(parentId);
        return ResponseEntity.success(categoryList);
    }
}
