package per.ccm.ygmall.search.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.search.manager.ESProductManager;
import per.ccm.ygmall.search.vo.result.ESProductResultVO;

@RestController
@RequestMapping("/search/product")
@Tag(name = "商品搜索接口", description = "商品搜索接口")
public class ProductSearchController {

    @Autowired
    private ESProductManager esProductManager;

    @GetMapping("/keyword")
    @Operation(summary = "关键词搜索", description = "传入商品名称、排序方式、页数、当前页进行关键词搜索")
    @Parameters({
            @Parameter(name = "keyword", description = "关键词"),
            @Parameter(name = "category_node", description = "分类节点"),
            @Parameter(name = "brand_id", description = "品牌ID"),
            @Parameter(name = "sort", description = "排序字段"),
            @Parameter(name = "order", description = "排序方式 asc-正序 desc-倒序"),
            @Parameter(name = "page_num", description = "当前页"),
            @Parameter(name = "page_size", description = "页数")
    })
    public ResponseEntity<ESProductResultVO> keywordSearch(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "category_node", required = false) String categoryNode,
            @RequestParam(value = "brand_id", required = false) Long brandId,
            @RequestParam(value = "sort", defaultValue = "product_id") String sort,
            @RequestParam(value = "order", defaultValue = "desc") String order,
            @RequestParam(value = "page_num", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize) throws Exception {
        ESProductResultVO result = esProductManager.keywordSearch(keyword, sort, order, categoryNode, brandId, pageNum, pageSize);
        return ResponseEntity.success(result);
    }
}
