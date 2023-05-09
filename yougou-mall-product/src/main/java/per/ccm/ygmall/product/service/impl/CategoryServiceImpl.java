package per.ccm.ygmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.common.cache.CacheNames;
import per.ccm.ygmall.common.exception.YougouException;
import per.ccm.ygmall.common.response.ResponseCode;
import per.ccm.ygmall.common.service.BaseService;
import per.ccm.ygmall.common.util.ConvertUtils;
import per.ccm.ygmall.common.vo.PageVO;
import per.ccm.ygmall.product.dto.CategoryDTO;
import per.ccm.ygmall.product.entity.Category;
import per.ccm.ygmall.product.mapper.CategoryMapper;
import per.ccm.ygmall.product.service.CategoryService;
import per.ccm.ygmall.product.vo.CategoryVO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl extends BaseService implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    @CacheEvict(cacheNames = CacheNames.PRODUCT_CATEGORY_CACHE_NAME, allEntries = true)
    public void save(CategoryDTO categoryDTO) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();

        // 判断分类是否已存在
        if (this.isExist(queryWrapper, categoryDTO)) {
            throw new YougouException(ResponseCode.PRODUCT_ERROR_B00001);
        }
        Category category = ConvertUtils.dtoConvertToEntity(categoryDTO, Category.class);
        categoryMapper.insert(category);
    }

    @Override
    @Cacheable(cacheNames = CacheNames.PRODUCT_CATEGORY_CACHE_NAME, key = "#parentId", sync = true)
    public List<CategoryVO> getCategoryList(Long parentId) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();

        // 获取所有分类
        List<CategoryVO> categoryList = ConvertUtils.converList(categoryMapper.selectList(queryWrapper), CategoryVO.class);
        // 获取当前分类
        return categoryList.stream()
                .filter(item -> ObjectUtils.nullSafeEquals(item.getParentId(), parentId))
                .peek(item -> item.setChildren(setChildrenCategoryList(item, categoryList))).collect(Collectors.toList());
    }

    @Override
    public PageVO<CategoryVO> getCategoryPages(Long parentId, Page<Category> page) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        // 查询条件
        queryWrapper.eq(!ObjectUtils.isEmpty(parentId), Category::getParentId, parentId);

        IPage<Category> pageInfo = categoryMapper.selectPage(page, queryWrapper);
        List<CategoryVO> categoryList = ConvertUtils.converList(pageInfo.getRecords(), CategoryVO.class);
        return new PageVO<>(pageInfo.getTotal(), categoryList);
    }

    @Override
    @CacheEvict(cacheNames = CacheNames.PRODUCT_CATEGORY_CACHE_NAME, allEntries = true)
    public void update(CategoryDTO categoryDTO) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();

        // 判断分类是否已存在
        if (this.isExist(queryWrapper, categoryDTO)) {
            throw new YougouException(ResponseCode.PRODUCT_ERROR_B00001);
        }
        Category category = ConvertUtils.dtoConvertToEntity(categoryDTO, Category.class);
        categoryMapper.updateById(category);
    }

    @Override
    @CacheEvict(cacheNames = CacheNames.PRODUCT_CATEGORY_CACHE_NAME, allEntries = true)
    public void batchDelete(List<Long> categoryIdList) {
        categoryMapper.deleteBatchIds(categoryIdList);
    }

    /**
     * 判断当该分类名称是否存在
     *
     * @param queryWrapper 查询
     * @param categoryDTO  分类传输数据
     * @return 是否存在该分类名称
     */
    private Boolean isExist(LambdaQueryWrapper<Category> queryWrapper, CategoryDTO categoryDTO) {
        Category categoryExist = categoryMapper.selectOne(queryWrapper.eq(Category::getName, categoryDTO.getName()));
        return !ObjectUtils.isEmpty(categoryExist);
    }

    /**
     * 设置子分类列表
     *
     * @param parentCategory  上级分类
     * @param allCategoryList 所有分类列表
     * @return 子分类列表
     */
    private List<CategoryVO> setChildrenCategoryList(CategoryVO parentCategory, List<CategoryVO> allCategoryList) {
        return allCategoryList.stream()
                .filter(item -> ObjectUtils.nullSafeEquals(item.getParentId(), parentCategory.getCategoryId()))
                .peek(item -> item.setChildren(setChildrenCategoryList(item, allCategoryList)))
                .collect(Collectors.toList());
    }
}
