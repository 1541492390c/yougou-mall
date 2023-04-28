package per.ccm.ygmall.product.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.common.controller.BaseController;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.product.dto.AttrDTO;
import per.ccm.ygmall.product.service.AttrService;
import per.ccm.ygmall.product.vo.AttrVO;

import java.util.List;

@RestController("adminAttrController")
@RequestMapping(("/admin/product/attr"))
@PreAuthorize("hasAnyRole(@roleConfig.SUPER_ADMIN, @roleConfig.COMMON_ADMIN)")
@Tag(name = "商品属性接口(管理员)", description = "商品属性接口(管理员)")
public class AttrController extends BaseController {

    @Autowired
    private AttrService attrService;

    @PostMapping("/save")
    @Operation(summary = "保存商品属性", description = "保存商品属性")
    public ResponseEntity<Void> save(@RequestBody AttrDTO attrDTO) throws Exception {
        attrService.save(attrDTO);
        return ResponseEntity.success();
    }

    @GetMapping("/list")
    @Operation(summary = "获取商品属性列表", description = "传入商品spuID获取该spu的商品属性值列表")
    @Parameter(name = "spuId", description = "spuID")
    public ResponseEntity<List<AttrVO>> getAttrListBySpuId(@RequestParam("spu_id") Long spuId) throws Exception {
        List<AttrVO> attrList = attrService.getAttrListBySpuId(spuId);
        return ResponseEntity.success(attrList);
    }

    @DeleteMapping("/batch_delete")
    public ResponseEntity<Void> batchDelete(@RequestBody List<Long> attrIdList) throws Exception {
        attrService.batchDelete(attrIdList);
        return ResponseEntity.success();
    }
}
