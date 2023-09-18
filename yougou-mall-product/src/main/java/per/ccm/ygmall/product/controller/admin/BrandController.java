package per.ccm.ygmall.product.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.common.basic.response.ResponseEntity;
import per.ccm.ygmall.common.basic.vo.PageVO;
import per.ccm.ygmall.product.dto.BrandDTO;
import per.ccm.ygmall.product.entity.Brand;
import per.ccm.ygmall.product.service.BrandService;
import per.ccm.ygmall.product.vo.BrandVO;

@RestController("adminBrandController")
@RequestMapping("/admin/product/brand")
@PreAuthorize("hasAnyRole(@roleConfig.SUPER_ADMIN, @roleConfig.COMMON_ADMIN)")
@Tag(name = "品牌接口(管理员)", description = "品牌接口(管理员)")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @PostMapping("/save")
    @Operation(summary = "保存品牌", description = "保存品牌")
    public ResponseEntity<Void> save(@RequestBody BrandDTO brandDTO) throws Exception {
        brandService.save(brandDTO);
        return ResponseEntity.success();
    }

    @GetMapping("/pages")
    @Operation(summary = "获取品牌分页信息", description = "获取品牌分页信息")
    @Parameters({
            @Parameter(name = "page_num", description = "当前页"),
            @Parameter(name = "page_size", description = "页数"),
            @Parameter(name = "category_node", description = "分类节点")
    })
    public ResponseEntity<PageVO<BrandVO>> getBrandPages(
            @RequestParam(value = "page_num", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "category_node", required = false) String categoryNode,
            @RequestParam(value = "name", required = false) String name) throws Exception {
        Page<Brand> page = new Page<>(pageNum, pageSize);
        PageVO<BrandVO> pageVO = brandService.getBrandPages(categoryNode, name, page);
        return ResponseEntity.success(pageVO);
    }

    @PutMapping("/update")
    @Operation(summary = "更新品牌信息", description = "更新品牌信息")
    public ResponseEntity<Void> update(@RequestBody BrandDTO brandDTO) throws Exception {
        brandService.update(brandDTO);
        return ResponseEntity.success();
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole(@roleConfig.SUPER_ADMIN)")
    @Operation(summary = "删除品牌", description = "传入品牌ID删除相关品牌")
    @Parameter(name = "brand_d", description = "品牌ID", required = true)
    public ResponseEntity<Void> delete(@RequestParam("brand_id") Long brandId) {
        brandService.removeById(brandId);
        return ResponseEntity.success();
    }
}
