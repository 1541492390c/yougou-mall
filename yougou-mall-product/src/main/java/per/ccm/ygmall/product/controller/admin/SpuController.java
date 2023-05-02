package per.ccm.ygmall.product.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.common.controller.BaseController;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.product.dto.SpuDTO;
import per.ccm.ygmall.product.service.SpuService;

@RestController("adminSpuController")
@RequestMapping("/admin/product/spu")
@PreAuthorize("hasAnyRole(@roleConfig.SUPER_ADMIN, @roleConfig.COMMON_ADMIN)")
@Tag(name = "spu接口(管理员)", description = "spu接口(管理员)")
public class SpuController extends BaseController {

    @Autowired
    private SpuService spuService;

    @PostMapping("/save")
    @Operation(summary = "保存spu", description = "保存spu")
    public ResponseEntity<Void> save(@RequestBody SpuDTO spuDTO) throws Exception {
        spuService.save(spuDTO);
        return ResponseEntity.success();
    }

    @PutMapping("/update")
    @Operation(summary = "更新spu信息", description = "更新spu信息")
    public ResponseEntity<Void> update(@RequestBody SpuDTO spuDTO) throws Exception {
        spuService.update(spuDTO);
        return ResponseEntity.success();
    }
}
