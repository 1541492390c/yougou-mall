package per.ccm.ygmall.product.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.common.basic.response.ResponseEntity;
import per.ccm.ygmall.product.dto.CategoryDTO;
import per.ccm.ygmall.product.service.CategoryService;

@RestController("adminCategoryController")
@RequestMapping("/admin/product/category")
@PreAuthorize("hasAnyRole(@roleConfig.SUPER_ADMIN, @roleConfig.COMMON_ADMIN)")
@Tag(name = "分类接口(管理员)", description = "分类接口(管理员)")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/save")
    @Operation(summary = "保存商品分类信息", description = "保存商品分类信息")
    public ResponseEntity<Void> save(@RequestBody CategoryDTO categoryDTO) throws Exception {
        categoryService.save(categoryDTO);
        return ResponseEntity.success();
    }

    @PutMapping("/update")
    @Operation(summary = "更新商品分类", description = "更新商品分类")
    public ResponseEntity<Void> update(@RequestBody CategoryDTO categoryDTO) throws Exception {
        categoryService.update(categoryDTO);
        return ResponseEntity.success();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole(@roleConfig.SUPER_ADMIN)")
    @Operation(summary = "删除分类", description = "传入分类ID删除分类")
    public ResponseEntity<Void> delete(@PathVariable("id") Long categoryId) throws Exception {
        categoryService.delete(categoryId);
        return ResponseEntity.success();
    }
}
