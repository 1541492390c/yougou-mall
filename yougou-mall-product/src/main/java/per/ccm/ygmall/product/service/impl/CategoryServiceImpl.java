package per.ccm.ygmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.common.cache.cache.CacheNames;
import per.ccm.ygmall.common.basic.exception.YougouException;
import per.ccm.ygmall.common.basic.response.ResponseCodeEnum;
import per.ccm.ygmall.common.basic.util.ConvertUtils;
import per.ccm.ygmall.product.dto.CategoryDTO;
import per.ccm.ygmall.product.entity.Category;
import per.ccm.ygmall.product.mapper.CategoryMapper;
import per.ccm.ygmall.product.service.CategoryService;
import per.ccm.ygmall.product.vo.CategoryVO;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(cacheNames = CacheNames.PRODUCT_CATEGORY_CACHE_NAME, allEntries = true)
    public void save(CategoryDTO categoryDTO) {
        // 判断分类是否已存在
        if (this.isExist(categoryDTO)) {
            throw new YougouException(ResponseCodeEnum.PRODUCT_ERROR_C0001);
        }
        Category category = ConvertUtils.convertProperties(categoryDTO, Category.class);
        categoryMapper.insert(category);
        // 插入后获取主键ID,插入分类节点
        if (category.getLevel() == 1) { // 顶级分类
            category.setNode(String.valueOf(category.getCategoryId()));
        } else if (category.getLevel() == 2 || category.getLevel() == 3) { // 其他分类
            LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
            Category parentCategory = categoryMapper.selectOne(queryWrapper.eq(Category::getCategoryId, category.getParentId()));
            // 父分类不存在
            if (ObjectUtils.isEmpty(parentCategory)) {
                throw new YougouException(ResponseCodeEnum.PRODUCT_ERROR_C0003);
            }
            // 设置分类节点
            category.setNode(parentCategory.getNode() + "-" + category.getCategoryId());
            // 更新分类
            categoryMapper.updateById(category);
        }
    }

    @Override
    @Cacheable(cacheNames = CacheNames.PRODUCT_CATEGORY_CACHE_NAME, key = "#parentId", sync = true)
    public List<CategoryVO> getCategoryList(Long parentId) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();

        // 获取所有分类
        List<CategoryVO> categoryList = ConvertUtils.converList(categoryMapper.selectList(queryWrapper), CategoryVO.class);
        // 获取当前分类
        return categoryList.stream().filter(item -> ObjectUtils.nullSafeEquals(item.getParentId(), parentId))
                .peek(item -> item.setChildren(this.setChildrenCategoryList(item, categoryList))).collect(Collectors.toList());
    }

    @Override
    public CategoryVO getCategoryByNode(String node) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();

        // 将节点转为分类ID列表
        List<Long> categoryIdList = Arrays.stream(node.split("-")).map(Long::valueOf).collect(Collectors.toList());
        // 获取分类列表
        queryWrapper.in(Category::getCategoryId, categoryIdList);
        List<CategoryVO> categoryList = ConvertUtils.converList(categoryMapper.selectList(queryWrapper), CategoryVO.class);
        // 当前分类节点不存在
        if (ObjectUtils.isEmpty(categoryList )) {
            throw new YougouException(ResponseCodeEnum.PRODUCT_ERROR_C0002);
        }
        // 获取当前分类
        List<CategoryVO> currentCategoryList = categoryList.stream().filter(item -> item.getParentId() == 0L)
                .peek(item -> item.setChildren(this.setChildrenCategoryList(item, categoryList))).collect(Collectors.toList());
        return currentCategoryList.get(0);
    }

    @Override
    @CacheEvict(cacheNames = CacheNames.PRODUCT_CATEGORY_CACHE_NAME, allEntries = true)
    public void update(CategoryDTO categoryDTO) {
        // 判断分类是否已存在
        if (this.isExist(categoryDTO)) {
            throw new YougouException(ResponseCodeEnum.PRODUCT_ERROR_C0001);
        }
        Category category = ConvertUtils.convertProperties(categoryDTO, Category.class);
        categoryMapper.updateById(category);
    }

    @Override
    @CacheEvict(cacheNames = CacheNames.PRODUCT_CATEGORY_CACHE_NAME, allEntries = true)
    public void batchRemove(List<Long> categoryIdList) {
        categoryMapper.deleteBatchIds(categoryIdList);
    }

    /**
     * 判断当该分类名称是否存在
     *
     * @param categoryDTO  分类传输数据
     * @return 是否存在该分类名称
     */
    private Boolean isExist(CategoryDTO categoryDTO) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
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
