package per.ccm.ygmall.product.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping("/save")
    @Operation(summary = "保存sku信息", description = "保存sku信息")
    public ResponseEntity<Void> save(@RequestBody SkuDTO skuDTO) throws Exception {
        skuService.save(skuDTO);
        return ResponseEntity.success();
    }
}
