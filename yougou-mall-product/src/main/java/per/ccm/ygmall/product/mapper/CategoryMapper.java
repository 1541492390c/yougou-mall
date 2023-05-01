package per.ccm.ygmall.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import per.ccm.ygmall.product.entity.Category;
import per.ccm.ygmall.product.vo.CategoryVO;

import java.util.List;

@Repository
public interface CategoryMapper extends BaseMapper<Category> {
    List<CategoryVO> selectCategoryList();

    IPage<CategoryVO> selectCategoryPages(@Param("parentId") Long parentId, Page<Category> page);
}
