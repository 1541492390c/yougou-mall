package per.ccm.ygmall.product.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.common.basic.response.ResponseEntity;
import per.ccm.ygmall.product.dto.SkuDTO;
import per.ccm.ygmall.product.service.SkuService;

@RestController("adminSkuController")
@RequestMapping("/admin/product/sku")
@PreAuthorize("hasAnyRole(@roleConfig.SUPER_ADMIN, @roleConfig.COMMON_ADMIN)")
@Tag(name = "sku接口", description = "sku接口")
public class SkuController {

    @Autowired
    private SkuService skuService;

    @PutMapping("/update")
    @Operation(summary = "更新sku", description = "更新sku")
    public ResponseEntity<Void> update(@RequestBody SkuDTO skuDTO) throws Exception {
        skuService.update(skuDTO);
        return ResponseEntity.success();
    }
}
