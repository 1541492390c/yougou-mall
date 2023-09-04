package per.ccm.ygmall.product.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.common.basic.response.ResponseEntity;
import per.ccm.ygmall.product.dto.BrandDTO;
import per.ccm.ygmall.product.service.BrandService;

@RestController("adminBrandController")
@RequestMapping("/admin/product/brand")
@PreAuthorize("hasAnyRole(@roleConfig.SUPER_ADMIN, @roleConfig.COMMON_ADMIN)")
@Tag(name = "品牌接口(管理员)", description = "品牌接口(管理员)")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @PutMapping("/update")
    @Operation(summary = "更新品牌信息", description = "更新品牌信息")
    public ResponseEntity<Void> update(@RequestBody BrandDTO brandDTO) throws Exception {
        brandService.update(brandDTO);
        return ResponseEntity.success();
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole(@roleConfig.SUPER_ADMIN)")
    @Operation(summary = "删除品牌", description = "传入品牌ID删除相关品牌")
    public ResponseEntity<Void> delete(@RequestParam("brand_id") Long brandId) {
        brandService.removeById(brandId);
        return ResponseEntity.success();
    }
}
