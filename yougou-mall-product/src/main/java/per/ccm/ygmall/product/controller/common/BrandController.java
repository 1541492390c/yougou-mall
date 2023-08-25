package per.ccm.ygmall.product.controller.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import per.ccm.ygmall.common.basic.response.ResponseEntity;
import per.ccm.ygmall.common.basic.vo.PageVO;
import per.ccm.ygmall.product.entity.Brand;
import per.ccm.ygmall.product.service.BrandService;
import per.ccm.ygmall.product.vo.BrandVO;

import java.util.List;

@RestController
@RequestMapping("/product/brand")
@Tag(name = "品牌接口", description = "品牌接口")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/pages")
    @Operation(summary = "获取品牌分页信息", description = "获取品牌分页信息")
    @Parameters({
            @Parameter(name = "page_num", description = "当前页"),
            @Parameter(name = "page_size", description = "页数")
    })
    public ResponseEntity<PageVO<BrandVO>> getBrandPages(
            @RequestParam(value = "page_num", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize) throws Exception {
        Page<Brand> page = new Page<>(pageNum, pageSize);
        PageVO<BrandVO> pageVO = brandService.getBrandPages(page);
        return ResponseEntity.success(pageVO);
    }

    @GetMapping("/list")
    @Operation(summary = "获取品牌列表", description = "获取品牌列表")
    @Parameter(name = "category_node", description = "分类节点")
    public ResponseEntity<List<BrandVO>> getBrandList(@RequestParam(value = "category_node", required = false) String categoryNode) throws Exception {
        List<BrandVO> brandList = brandService.getBrandList(categoryNode);
        return ResponseEntity.success(brandList);
    }
}
