package per.ccm.ygmall.product.controller.common;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.product.service.AttrService;
import per.ccm.ygmall.product.vo.AttrVO;

import java.util.List;

@RestController
@RequestMapping("/product/attr")
@Tag(name = "商品属性接口", description = "商品属性接口")
public class AttrController {

    @Autowired
    private AttrService attrService;

    @GetMapping("/list")
    @Operation(summary = "获取商品属性列表", description = "传入商品商品ID获取该商品的商品属性值列表")
    @Parameter(name = "product_id", description = "商品ID")
    public ResponseEntity<List<AttrVO>> getAttrListBySpuId(@RequestParam("product_id") Long productId) throws Exception {
        List<AttrVO> attrList = attrService.getAttrListByProductId(productId);
        return ResponseEntity.success(attrList);
    }
}
