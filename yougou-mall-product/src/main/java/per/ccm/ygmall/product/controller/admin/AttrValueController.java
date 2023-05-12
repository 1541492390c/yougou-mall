package per.ccm.ygmall.product.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.product.dto.AttrValueDTO;
import per.ccm.ygmall.product.service.AttrValueService;

import java.util.List;

@RestController("adminAttrValueController")
@RequestMapping("/admin/product/attr_value")
@PreAuthorize("hasAnyRole(@roleConfig.SUPER_ADMIN, @roleConfig.COMMON_ADMIN)")
@Tag(name = "商品属性值接口(管理员)", description = "商品属性值接口(管理员)")
public class AttrValueController {

    @Autowired
    private AttrValueService attrValueService;

    @PostMapping("/batch_save")
    @Operation(summary = "批量保存商品属性值", description = "批量保存商品属性值")
    public ResponseEntity<Void> batchSave(@RequestBody List<AttrValueDTO> attrValueDTOList) throws Exception {
        attrValueService.batchSave(attrValueDTOList);
        return ResponseEntity.success();
    }

    @DeleteMapping("/batch_delete")
    @Operation(summary = "批量删除商品属性值", description = "批量删除商品属性值")
    public ResponseEntity<Void> batchDelete(@RequestBody List<Long> attrValueIdList) throws Exception {
        attrValueService.batchDelete(attrValueIdList);
        return ResponseEntity.success();
    }
}
