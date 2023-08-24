package per.ccm.ygmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.ccm.ygmall.common.util.ConvertUtils;
import per.ccm.ygmall.database.vo.PageVO;
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
    public PageVO<BrandVO> getBrandPages(Page<Brand> page) {
        Page<Brand> pageInfo = brandMapper.selectPage(page, new LambdaQueryWrapper<>());
        List<BrandVO> brandList = ConvertUtils.converList(pageInfo.getRecords(), BrandVO.class);
        return new PageVO<>(pageInfo.getTotal(), brandList);
    }

    @Override
    public List<BrandVO> getBrandList(String categoryNode) {
        LambdaQueryWrapper<Brand> queryWrapper = new LambdaQueryWrapper<>();
        List<Brand> brandList = brandMapper.selectList(queryWrapper.eq(Brand::getCategoryNode, categoryNode));
        return ConvertUtils.converList(brandList, BrandVO.class);
    }
}
