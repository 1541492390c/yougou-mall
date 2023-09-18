package per.ccm.ygmall.product.controller.common;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import per.ccm.ygmall.common.basic.response.ResponseEntity;
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

    @GetMapping("/by_node")
    @Operation(summary = "根据分类节点获取分类", description = "根据分类节点获取分类")
    @Parameter(name = "node", description = "分类节点", required = true)
    public ResponseEntity<CategoryVO> getCategoryByNode(@RequestParam("node") String node) throws Exception {
        CategoryVO category = categoryService.getCategoryByNode(node);
        return ResponseEntity.success(category);
    }
}
