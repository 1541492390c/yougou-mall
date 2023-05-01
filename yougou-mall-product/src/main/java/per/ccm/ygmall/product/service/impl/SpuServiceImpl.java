package per.ccm.ygmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.common.cache.CacheNames;
import per.ccm.ygmall.common.exception.YougouException;
import per.ccm.ygmall.common.response.ResponseCode;
import per.ccm.ygmall.common.service.BaseService;
import per.ccm.ygmall.common.util.ConvertUtils;
import per.ccm.ygmall.common.vo.PageVO;
import per.ccm.ygmall.product.dto.SpuDTO;
import per.ccm.ygmall.product.entity.Spu;
import per.ccm.ygmall.product.mapper.SpuMapper;
import per.ccm.ygmall.product.service.SpuService;
import per.ccm.ygmall.product.vo.SpuVO;

import java.util.List;

@Service
public class SpuServiceImpl extends BaseService implements SpuService {

    @Autowired
    private SpuMapper spuMapper;

    @Override
    public void save(SpuDTO spuDTO) {
        LambdaQueryWrapper<Spu> queryWrapper = new LambdaQueryWrapper<>();

        // 判断商品名称是否存在
        if (this.isExist(queryWrapper, spuDTO)) {
            throw new YougouException(ResponseCode.PRODUCT_ERROR_B10001);
        }
        Spu spu = ConvertUtils.dtoConvertToEntity(spuDTO, Spu.class);
        spuMapper.insert(spu);
    }

    @Override
    @Cacheable(cacheNames = CacheNames.PRODUCT_SPU_CACHE_NAME, key = "'recommended-list'", sync = true)
    public List<SpuVO> getRecommendedSpuList() {
        return spuMapper.selectRecommendedSpuList();
    }

    @Override
    public PageVO<SpuVO> getSpuPages(String categories, Page<Spu> page) {
        IPage<SpuVO> pageInfo = spuMapper.selectSpuPages(categories, page);
        return new PageVO<>(pageInfo.getTotal(), pageInfo.getRecords());
    }

    @Override
    public void update(SpuDTO spuDTO) {
        LambdaQueryWrapper<Spu> queryWrapper = new LambdaQueryWrapper<>();

        // 判断商品名称是否存在
        if (this.isExist(queryWrapper, spuDTO)) {
            throw new YougouException(ResponseCode.PRODUCT_ERROR_B10001);
        }
        Spu spu = ConvertUtils.dtoConvertToEntity(spuDTO, Spu.class);
        spuMapper.updateById(spu);
    }

    /**
     * 判断当该spu名称是否存在
     *
     * @param queryWrapper 查询
     * @param spuDTO spu传输数据
     * @return 是否存在该spu名称
     * */
    private Boolean isExist(LambdaQueryWrapper<Spu> queryWrapper, SpuDTO spuDTO) {
        Spu spuExist = spuMapper.selectOne(queryWrapper.eq(Spu::getName, spuDTO.getName()));
        return !ObjectUtils.isEmpty(spuExist);
    }
}
