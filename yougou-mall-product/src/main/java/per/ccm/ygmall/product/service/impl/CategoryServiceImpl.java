package per.ccm.ygmall.product.service.impl;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAUpdateClause;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
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
import per.ccm.ygmall.product.entity.QCategory;
import per.ccm.ygmall.product.repository.CategoryRepository;
import per.ccm.ygmall.product.service.CategoryService;
import per.ccm.ygmall.product.vo.CategoryVO;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl extends BaseService implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @CacheEvict(cacheNames = CacheNames.PRODUCT_CATEGORY_CACHE_NAME, allEntries = true)
    public void save(CategoryDTO categoryDTO) {
        QCategory qCategory = QCategory.category;

        Optional<Category> categoryExist = categoryRepository.findOne(qCategory.name.eq(categoryDTO.getName()));
        // 判断分类名称是否存在
        if (categoryExist.isPresent()) {
            throw new YougouException(ResponseCode.PRODUCT_ERROR_B00001);
        }
        Category category = ConvertUtils.dtoConvertToEntity(categoryDTO, Category.class);
        categoryRepository.save(category);
    }

    @Override
    @Cacheable(cacheNames = CacheNames.PRODUCT_CATEGORY_CACHE_NAME, key = "#parentId", sync = true)
    public List<CategoryVO> getCategoryList(Long parentId) {
        QCategory qCategory = QCategory.category;
        QBean<CategoryVO> qBean = this.getQBean(qCategory);

        // 获取所有分类
        List<CategoryVO> categoryList = super.jpaQueryFactory.select(qBean).from(qCategory).fetch();
        // 获取当前分类
        List<CategoryVO> parentCategoryList = categoryList.stream()
                .filter(item -> ObjectUtils.nullSafeEquals(item.getParentId(), parentId)).collect(Collectors.toList());
        this.setChildrenCategoryList(parentCategoryList, categoryList);
        return parentCategoryList;
    }

    @Override
    public PageVO<CategoryVO> getCategoryPages(Pageable pageable) {
        QCategory qCategory = QCategory.category;
        QBean<CategoryVO> qBean = this.getQBean(qCategory);

        Long total = categoryRepository.count();
        List<CategoryVO> categoryList = super.jpaQueryFactory.select(qBean).from(qCategory).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
        return new PageVO<>(total, categoryList);
    }

    @Override
    @CacheEvict(cacheNames = CacheNames.PRODUCT_CATEGORY_CACHE_NAME, allEntries = true)
    public void update(CategoryDTO categoryDTO) {
        QCategory qCategory = QCategory.category;

        Optional<Category> categoryExist = categoryRepository.findOne(qCategory.name.eq(categoryDTO.getName()));
        // 判断分类名称是否存在
        if (categoryExist.isPresent()) {
            throw new YougouException(ResponseCode.PRODUCT_ERROR_B00001);
        }

        JPAUpdateClause jpaUpdateClause = super.jpaQueryFactory.update(qCategory);
        // 更新分类名称
        if (!ObjectUtils.isEmpty(categoryDTO.getName())) {
            jpaUpdateClause.set(qCategory.name, categoryDTO.getName());
        }
        jpaUpdateClause.where(qCategory.categoryId.eq(categoryDTO.getCategoryId())).execute();
    }

    @Override
    @CacheEvict(cacheNames = CacheNames.PRODUCT_CATEGORY_CACHE_NAME, allEntries = true)
    public void batchDelete(List<Long> categoryIdList) {
        QCategory qCategory = QCategory.category;

        // 删除分类并删除其子分类
        Predicate predicate = qCategory.categoryId.in(categoryIdList).and(qCategory.parentId.in(categoryIdList));
        super.jpaQueryFactory.delete(qCategory).where(predicate).execute();
    }

    /**
     * 获取映射对象
     *
     * @param qCategory querydsl实体
     * @return 映射对象
     * */
    private QBean<CategoryVO> getQBean(QCategory qCategory) {
        return Projections.bean(CategoryVO.class, qCategory.categoryId, qCategory.parentId, qCategory.name, qCategory.level);
    }

    /**
     * 设置子分类列表
     *
     * @param categoryList 上级分类列表
     * @param childrenCategoryList 子分类列表
     * */
    private void setChildrenCategoryList(List<CategoryVO> categoryList, List<CategoryVO> childrenCategoryList) {
        if (ObjectUtils.isEmpty(categoryList) || ObjectUtils.isEmpty(childrenCategoryList)) {
            return;
        }
        Map<Long, List<CategoryVO>> map = childrenCategoryList.stream().collect(Collectors.groupingBy(CategoryVO::getParentId));
        for (CategoryVO category : categoryList) {
            category.setChildren(map.get(category.getCategoryId()));
        }
    }
}
