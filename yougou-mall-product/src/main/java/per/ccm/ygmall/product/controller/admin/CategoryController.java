package per.ccm.ygmall.product.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.common.controller.BaseController;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.common.vo.PageVO;
import per.ccm.ygmall.product.dto.CategoryDTO;
import per.ccm.ygmall.product.entity.Category;
import per.ccm.ygmall.product.service.CategoryService;
import per.ccm.ygmall.product.vo.CategoryVO;

import java.util.List;

@RestController("adminCategoryController")
@RequestMapping("/admin/product/category")
@PreAuthorize("hasAnyRole(@roleConfig.SUPER_ADMIN, @roleConfig.COMMON_ADMIN)")
@Tag(name = "分类接口(管理员)", description = "分类接口(管理员)")
public class CategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/save")
    @Operation(summary = "保存商品分类信息", description = "保存商品分类信息")
    public ResponseEntity<Void> save(@RequestBody CategoryDTO categoryDTO) throws Exception {
        categoryService.save(categoryDTO);
        return ResponseEntity.success();
    }

    @GetMapping("/pages")
    @Operation(summary = "分页获取分类信息列表", description = "分页获取分类信息列表")
    @Parameters({
            @Parameter(name = "pageNum", description = "当前页"),
            @Parameter(name = "pageSize", description = "页数"),
            @Parameter(name = "parentId", description = "父级分类ID")})
    public ResponseEntity<PageVO<CategoryVO>> getCategoryPages(
            @RequestParam(value = "page_num", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "parent_id", required = false) Long parentId) throws Exception {
        Page<Category> page = new Page<>(pageNum, pageSize);
        PageVO<CategoryVO> pageVO = categoryService.getCategoryPages(parentId, page);
        return ResponseEntity.success(pageVO);
    }

    @PutMapping("/update")
    @Operation(summary = "更新商品分类", description = "更新商品分类")
    public ResponseEntity<Void> update(@RequestBody CategoryDTO categoryDTO) throws Exception {
        categoryService.update(categoryDTO);
        return ResponseEntity.success();
    }

    @DeleteMapping("/batch_delete")
    @Operation(summary = "批量删除商品分类", description = "根据商品分类ID批量删除商品分类")
    public ResponseEntity<Void> batchDelete(@RequestBody List<Long> categoryIdList) throws Exception {
        categoryService.batchDelete(categoryIdList);
        return ResponseEntity.success();
    }
}
