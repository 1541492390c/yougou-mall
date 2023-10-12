package per.ccm.ygmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.common.basic.util.ConvertUtils;
import per.ccm.ygmall.common.basic.vo.PageVO;
import per.ccm.ygmall.product.dto.BrandDTO;
import per.ccm.ygmall.product.entity.Brand;
import per.ccm.ygmall.product.mapper.BrandMapper;
import per.ccm.ygmall.product.service.BrandService;
import per.ccm.ygmall.product.vo.BrandVO;

import java.util.List;

@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public void save(BrandDTO brandDTO) throws Exception {
        Brand brand = ConvertUtils.convertProperties(brandDTO, Brand.class);
        brandMapper.insert(brand);
    }

    @Override
    public PageVO<BrandVO> getBrandPages(Page<Brand> page) {
        Page<Brand> pageInfo = brandMapper.selectPage(page, new LambdaQueryWrapper<>());
        List<BrandVO> brandList = ConvertUtils.converList(pageInfo.getRecords(), BrandVO.class);
        return new PageVO<>(pageInfo.getTotal(), brandList);
    }

    @Override
    public PageVO<BrandVO> getBrandPages(String categoryNode, String name, Page<Brand> page) {
        LambdaQueryWrapper<Brand> queryWrapper = new LambdaQueryWrapper<>();

        // 分类节点
        if (!ObjectUtils.isEmpty(categoryNode)) {
            queryWrapper.eq(Brand::getCategoryNode, categoryNode);
        }
        // 品牌名称
        if (!ObjectUtils.isEmpty(name)) {
            queryWrapper.like(Brand::getName, name);
        }
        Page<Brand> pageInfo = brandMapper.selectPage(page, queryWrapper);
        List<BrandVO> brandList = ConvertUtils.converList(pageInfo.getRecords(), BrandVO.class);
        return new PageVO<>(pageInfo.getTotal(), brandList);
    }

    @Override
    public List<BrandVO> getBrandList(String categoryNode) {
        LambdaQueryWrapper<Brand> queryWrapper = new LambdaQueryWrapper<>();

        // 分类节点
        if (!ObjectUtils.isEmpty(categoryNode)) {
            queryWrapper.eq(Brand::getCategoryNode, categoryNode);
        }
        List<Brand> brandList = brandMapper.selectList(queryWrapper);
        return ConvertUtils.converList(brandList, BrandVO.class);
    }

    @Override
    public void update(BrandDTO brandDTO) throws Exception {
        Brand brand = ConvertUtils.convertProperties(brandDTO, Brand.class);
        brandMapper.updateById(brand);
    }
}
