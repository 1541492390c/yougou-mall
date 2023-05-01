package per.ccm.ygmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.common.exception.YougouException;
import per.ccm.ygmall.common.response.ResponseCode;
import per.ccm.ygmall.common.service.BaseService;
import per.ccm.ygmall.common.util.ConvertUtils;
import per.ccm.ygmall.product.dto.SkuDTO;
import per.ccm.ygmall.product.entity.Sku;
import per.ccm.ygmall.product.mapper.SkuMapper;
import per.ccm.ygmall.product.service.SkuService;
import per.ccm.ygmall.product.vo.SkuVO;

import java.util.List;

@Service
public class SkuServiceImpl extends BaseService implements SkuService {

    @Autowired
    private SkuMapper skuMapper;

    @Override
    public void save(SkuDTO skuDTO) throws Exception {
        LambdaQueryWrapper<Sku> queryWrapper = new LambdaQueryWrapper<>();

        // 判断sku规格是否存在
        if (this.isExist(queryWrapper, skuDTO)) {
            throw new YougouException(ResponseCode.PRODUCT_ERROR_B40001);
        }
        Sku sku = ConvertUtils.dtoConvertToEntity(skuDTO, Sku.class);
        skuMapper.insert(sku);
    }

    @Override
    public List<SkuVO> getSkuListBySpuId(Long spuId) {
        LambdaQueryWrapper<Sku> queryWrapper = new LambdaQueryWrapper<>();
        List<Sku> skuList = skuMapper.selectList(queryWrapper.eq(Sku::getSpuId, spuId));
        return ConvertUtils.converList(skuList, SkuVO.class);
    }

    /**
     * 判断当该sku规格是否存在
     *
     * @param queryWrapper 查询
     * @param skuDTO sku传输数据
     * @return 是否存在该sku名称
     * */
    private Boolean isExist(LambdaQueryWrapper<Sku> queryWrapper, SkuDTO skuDTO) {
        Sku skuExist = skuMapper.selectOne(queryWrapper.eq(Sku::getSkuDesc, skuDTO.getSkuDesc()));
        return !ObjectUtils.isEmpty(skuExist);
    }
}
