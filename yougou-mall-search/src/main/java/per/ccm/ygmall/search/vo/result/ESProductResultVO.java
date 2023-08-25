package per.ccm.ygmall.search.vo.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.basic.vo.BaseVO;
import per.ccm.ygmall.common.basic.vo.PageVO;
import per.ccm.ygmall.search.vo.ESBrandVO;
import per.ccm.ygmall.search.vo.ESCategoryVO;
import per.ccm.ygmall.search.vo.ESProductVO;

import java.util.List;

@Setter
@Getter
@ToString
public class ESProductResultVO extends BaseVO {

    @Schema(description = "商品分页搜索结果")
    private PageVO<ESProductVO> productPage;

    @Schema(description = "分类搜索结果")
    private List<ESCategoryVO> categoryList;

    @Schema(description = "品牌搜索结果")
    private List<ESBrandVO> brandList;

    public ESProductResultVO(PageVO<ESProductVO> esPageVO, List<ESCategoryVO> categoryList, List<ESBrandVO> brandList) {
        this.productPage = esPageVO;
        this.categoryList = categoryList;
        this.brandList = brandList;
    }
}
