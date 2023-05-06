package per.ccm.ygmall.product.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.common.controller.BaseController;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.product.dto.ProductDTO;
import per.ccm.ygmall.product.service.ProductService;

@RestController("adminSpuController")
@RequestMapping("/admin/product")
@PreAuthorize("hasAnyRole(@roleConfig.SUPER_ADMIN, @roleConfig.COMMON_ADMIN)")
@Tag(name = "商品接口(管理员)", description = "商品接口(管理员)")
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    @Operation(summary = "保存商品", description = "保存商品")
    public ResponseEntity<Void> save(@RequestBody ProductDTO productDTO) throws Exception {
        productService.save(productDTO);
        return ResponseEntity.success();
    }

    @PutMapping("/update")
    @Operation(summary = "更新商品信息", description = "更新商品信息")
    public ResponseEntity<Void> update(@RequestBody ProductDTO productDTO) throws Exception {
        productService.update(productDTO);
        return ResponseEntity.success();
    }
}
