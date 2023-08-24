package per.ccm.ygmall.search.manager;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchPhraseQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.HitsMetadata;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.common.exception.YougouException;
import per.ccm.ygmall.common.response.ResponseCodeEnum;
import per.ccm.ygmall.common.vo.PageVO;
import per.ccm.ygmall.search.enums.IndexEnum;
import per.ccm.ygmall.search.vo.ESBrandVO;
import per.ccm.ygmall.search.vo.ESCategoryVO;
import per.ccm.ygmall.search.vo.ESProductVO;
import per.ccm.ygmall.search.vo.base.ESBaseVO;
import per.ccm.ygmall.search.vo.result.ESProductResultVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ESProductManager {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    /**
     * 关键词搜索
     *
     * @param keyword 关键词
     * @param sort 排序字段
     * @param order 排序方式
     * @param pageNum 当前页
     * @param pageSize 页数
     * @return 商品搜索结果
     * */
    public ESProductResultVO keywordSearch(String keyword, String sort, String order, String categoryNode, Long brandId, Integer pageNum, Integer pageSize) throws Exception {
        // 商品分页搜索结果
        PageVO<ESProductVO> esPageVO = this.searchProduct(keyword, sort, order, categoryNode, brandId, pageNum, pageSize);
        // 分类搜索结果
        List<ESCategoryVO> esCategoryList = this.searchCategory(keyword, categoryNode);
        // 品牌搜索结果
        List<ESBrandVO> esBrandList = this.searchBrand(keyword, categoryNode, brandId);
        return new ESProductResultVO(esPageVO, esCategoryList, esBrandList);
    }

    /**
     * 搜索商品
     *
     * @param keyword 关键词
     * @param sort 排序字段
     * @param order 排序方式
     * @param pageNum 当前页
     * @param pageSize 页数
     * @return 商品搜索分页
     * */
    private PageVO<ESProductVO> searchProduct(String keyword, String sort, String order, String categoryNode, Long brandId, Integer pageNum, Integer pageSize) throws IOException {
        List<Query> queryList = new ArrayList<>();
        // 根据关键词搜索
        if (!ObjectUtils.isEmpty(keyword)) {
            MatchPhraseQuery matchPhraseQuery = QueryBuilders.matchPhrase().field("name").query(keyword).slop(5).build();
            queryList.add(matchPhraseQuery._toQuery());
        }
        // 根据分类节点搜索
        if (!ObjectUtils.isEmpty(categoryNode)) {
            TermQuery termQuery = QueryBuilders.term().field("category_node.keyword").value(categoryNode).build();
            queryList.add(termQuery._toQuery());
        }
        // 根据品牌ID搜索
        if (!ObjectUtils.isEmpty(brandId)) {
            TermQuery termQuery = QueryBuilders.term().field("brand_id").value(brandId).build();
            queryList.add(termQuery._toQuery());
        }

        SearchResponse<Object> searchResponse = elasticsearchClient.search(request -> request.index(IndexEnum.PRODUCT_INDEX.getValue())
                .query(query -> query.bool(bool -> bool.must(queryList)))
                .sort(s -> s.field(field -> field.field(sort).order(ObjectUtils.nullSafeEquals(order, "asc") ? SortOrder.Asc : SortOrder.Desc)))
                .from((pageNum - 1) * pageSize).size(pageSize), Object.class);
        return this.createResult(searchResponse.hits(), ESProductVO.class);
    }

    /**
     * 搜索分类
     * @param keyword 关键词
     * @return 分类搜索列表
     * */
    private List<ESCategoryVO> searchCategory(String keyword, String categoryNode) throws IOException {
        List<Query> queryList = new ArrayList<>();
        if (!ObjectUtils.isEmpty(keyword)) {
            MatchPhraseQuery matchPhraseQuery = QueryBuilders.matchPhrase().field("name").query(keyword).slop(5).build();
            queryList.add(matchPhraseQuery._toQuery());
        }
        if (!ObjectUtils.isEmpty(categoryNode)) {
            TermQuery termQuery = QueryBuilders.term().field("node.keyword").value(categoryNode).build();
            queryList.add(termQuery._toQuery());
        }
        SearchResponse<Object> searchResponse = elasticsearchClient.search(request -> request.index(IndexEnum.CATEGORY_INDEX.getValue())
                .query(query -> query.bool(bool -> bool.must(queryList))), Object.class);
        PageVO<ESCategoryVO> pageVO = this.createResult(searchResponse.hits(), ESCategoryVO.class);
        return pageVO.getList();
    }

    /**
     * 搜索品牌
     * @param keyword 关键词
     * @return 品牌搜索列表
     * */
    public List<ESBrandVO> searchBrand(String keyword, String categoryNode, Long brandId) throws IOException {
        List<Query> queryList = new ArrayList<>();
        if (!ObjectUtils.isEmpty(keyword)) {
            MatchPhraseQuery matchPhraseQuery = QueryBuilders.matchPhrase().field("name").query(keyword).slop(5).build();
            queryList.add(matchPhraseQuery._toQuery());
        }
        if (!ObjectUtils.isEmpty(categoryNode)) {
            TermQuery termQuery = QueryBuilders.term().field("category_node.keyword").value(categoryNode).build();
            queryList.add(termQuery._toQuery());
        }
        if (!ObjectUtils.isEmpty(brandId)) {
            TermQuery termQuery = QueryBuilders.term().field("brand_id").value(brandId).build();
            queryList.add(termQuery._toQuery());
        }
        SearchResponse<Object> searchResponse = elasticsearchClient.search(request -> request.index(IndexEnum.BRAND_INDEX.getValue())
                .query(query -> query.bool(bool -> bool.must(queryList))), Object.class);
        PageVO<ESBrandVO> pageVO = this.createResult(searchResponse.hits(), ESBrandVO.class);
        return pageVO.getList();
    }

    /**
     * 构建搜索结果
     * @param hits 搜索结果
     * @param clazz 类型
     * */
    private <E extends ESBaseVO> PageVO<E> createResult(HitsMetadata<Object> hits, Class<E> clazz) throws JsonProcessingException {
        List<E> esList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        for (Hit<Object> hit : hits.hits()) {
            // 转换为VO
            E es = objectMapper.readValue(objectMapper.writeValueAsString(hit.source()), clazz);
            // 去除搜索内部标记
            es.setType(null);
            // 添加商品搜索
            esList.add(es);
        }
        if (!ObjectUtils.isEmpty(hits.total())) {
            return new PageVO<>(hits.total().value(), esList);
        }
        throw new YougouException(ResponseCodeEnum.SERVER_ERROR_00001);
    }
}
