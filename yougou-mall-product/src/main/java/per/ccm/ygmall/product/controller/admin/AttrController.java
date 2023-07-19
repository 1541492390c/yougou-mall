package per.ccm.ygmall.product.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.product.dto.AttrDTO;
import per.ccm.ygmall.product.service.AttrService;

import java.util.List;

@RestController("adminAttrController")
@RequestMapping("/admin/product/attr")
@PreAuthorize("hasAnyRole(@roleConfig.SUPER_ADMIN, @roleConfig.COMMON_ADMIN)")
@Tag(name = "商品属性接口(管理员)", description = "商品属性接口(管理员)")
public class AttrController {

    @Autowired
    private AttrService attrService;

    @PostMapping("/save")
    @Operation(summary = "保存商品属性", description = "保存商品属性")
    public ResponseEntity<Void> save(@RequestBody AttrDTO attrDTO) throws Exception {
        attrService.save(attrDTO);
        return ResponseEntity.success();
    }

    @PutMapping("/update")
    @Operation(summary = "更新商品属性", description = "更新商品属性")
    public ResponseEntity<Void> update(@RequestBody AttrDTO attrDTO) throws Exception {
        attrService.update(attrDTO);
        return ResponseEntity.success();
    }

    @DeleteMapping("/batch_delete")
    @Operation(summary = "批量删除商品属性", description = "批量删除商品属性")
    public ResponseEntity<Void> batchDelete(@RequestBody List<Long> attrIdList) throws Exception {
        attrService.batchRemove(attrIdList);
        return ResponseEntity.success();
    }
}
