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
import per.ccm.ygmall.product.dto.SkuDTO;
import per.ccm.ygmall.product.entity.Sku;
import per.ccm.ygmall.product.service.SkuService;
import per.ccm.ygmall.product.vo.SkuVO;

@RestController("adminSkuController")
@RequestMapping("/admin/product/sku")
@PreAuthorize("hasAnyRole(@roleConfig.SUPER_ADMIN, @roleConfig.COMMON_ADMIN)")
@Tag(name = "sku接口", description = "sku接口")
public class SkuController {

    @Autowired
    private SkuService skuService;

    @GetMapping("/pages")
    @Operation(summary = "获取商品sku信息列表", description = "获取商品sku信息列表")
    @Parameters({
            @Parameter(name = "product_id", description = "商品ID", required = true),
            @Parameter(name = "page_num", description = "当前页"),
            @Parameter(name = "page_size", description = "页数")
    })
    public ResponseEntity<PageVO<SkuVO>> getSkuPages(
            @RequestParam("product_id") Long productId,
            @RequestParam(value = "page_num", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "page_size", defaultValue = "5") Integer pageSize) throws Exception {
        Page<Sku> page = new Page<>(pageNum, pageSize);
        PageVO<SkuVO> pageVO = skuService.getSkuPages(productId, page);
        return ResponseEntity.success(pageVO);
    }

    @PutMapping("/update")
    @Operation(summary = "更新sku信息", description = "更新sku信息")
    public ResponseEntity<Void> update(@RequestBody SkuDTO skuDTO) throws Exception {
        skuService.update(skuDTO);
        return ResponseEntity.success();
    }
}
