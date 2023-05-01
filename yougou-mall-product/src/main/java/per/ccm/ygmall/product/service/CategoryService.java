package per.ccm.ygmall.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.data.domain.Pageable;
import per.ccm.ygmall.common.vo.PageVO;
import per.ccm.ygmall.product.dto.CategoryDTO;
import per.ccm.ygmall.product.entity.Category;
import per.ccm.ygmall.product.vo.CategoryVO;

import java.util.List;

public interface CategoryService {
    /**
     * 保存分类信息
     *
     * @param categoryDTO 分类信息传输列表
     * */
    void save(CategoryDTO categoryDTO) throws Exception;
    /**
     * 获取分类信息列表
     *
     * @param parentId 父级分类ID
     * @return 分类信息列表
     * */
    List<CategoryVO> getCategoryList(Long parentId) throws Exception;

    /**
     * 分页获取分类信息
     *
     * @param page 分页信息
     * @return 分类信息分页信息列表
     * */
    PageVO<CategoryVO> getCategoryPages(Long parentId, Page<Category> page) throws Exception;

    /**
     * 更新商品分类信息
     *
     * @param categoryDTO 分类信息传输数据
     * */
    void update(CategoryDTO categoryDTO) throws Exception;

    /**
     * 根据商品分类ID批量删除商品分类
     *
     * @param categoryIdList 分类ID列表
     * */
    void batchDelete(List<Long> categoryIdList) throws Exception;
}
