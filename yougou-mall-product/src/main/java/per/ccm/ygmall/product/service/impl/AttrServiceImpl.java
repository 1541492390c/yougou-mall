package per.ccm.ygmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.common.exception.YougouException;
import per.ccm.ygmall.common.response.ResponseCode;
import per.ccm.ygmall.common.service.BaseService;
import per.ccm.ygmall.common.util.ConvertUtils;
import per.ccm.ygmall.product.dto.AttrDTO;
import per.ccm.ygmall.product.entity.Attr;
import per.ccm.ygmall.product.mapper.AttrMapper;
import per.ccm.ygmall.product.service.AttrService;
import per.ccm.ygmall.product.service.AttrValueService;
import per.ccm.ygmall.product.vo.AttrVO;

import java.util.List;

@Service
public class AttrServiceImpl extends BaseService implements AttrService {

    @Autowired
    private AttrMapper attrMapper;

    @Autowired
    private AttrValueService attrValueService;

    @Override
    public void save(AttrDTO attrDTO) {
        LambdaQueryWrapper<Attr> queryWrapper = new LambdaQueryWrapper<>();

        // 判断当前spu下是否存在该属性名称
        if (this.isExist(queryWrapper, attrDTO)) {
            throw new YougouException(ResponseCode.PRODUCT_ERROR_B2001);
        }
        // 一个spu最多拥有5个属性
        if (attrMapper.selectCount(queryWrapper.eq(Attr::getProductId, attrDTO.getProductId())) >= 5) {
            throw new YougouException(ResponseCode.PRODUCT_ERROR_B20002);
        }
        Attr attr = ConvertUtils.dtoConvertToEntity(attrDTO, Attr.class);
        attrMapper.insert(attr);
    }

    @Override
    public List<AttrVO> getAttrListByProductId(Long productId) {
        return attrMapper.selectAttrList(productId);
    }

    @Override
    public List<AttrVO> getAttrListByAttrIdList(List<Long> attrIdList) {
        LambdaQueryWrapper<Attr> queryWrapper = new LambdaQueryWrapper<>();
        List<Attr> attrList = attrMapper.selectList(queryWrapper.in(Attr::getAttrId, attrIdList));
        return ConvertUtils.converList(attrList, AttrVO.class);
    }

    @Override
    public void update(AttrDTO attrDTO) {
        LambdaQueryWrapper<Attr> queryWrapper = new LambdaQueryWrapper<>();

        // 判断当前spu下是否存在该属性名称
        if (this.isExist(queryWrapper, attrDTO)) {
            throw new YougouException(ResponseCode.PRODUCT_ERROR_B2001);
        }
        Attr attr = ConvertUtils.dtoConvertToEntity(attrDTO, Attr.class);
        attrMapper.updateById(attr);
    }

    @Override
    public void batchDelete(List<Long> attrIdList) throws Exception {
        attrMapper.deleteBatchIds(attrIdList);
        // 删除对应的商品属性值
        attrValueService.batchDeleteByAttrIdList(attrIdList);
    }

    /**
     * 判断当前spu下是否存在该属性名称
     *
     * @param queryWrapper 查询
     * @param attrDTO 属性传输数据
     * @return 是否存在该属性名称
     * */
    private Boolean isExist(LambdaQueryWrapper<Attr> queryWrapper, AttrDTO attrDTO) {
        Attr attrExist = attrMapper.selectOne(queryWrapper.eq(Attr::getProductId, attrDTO.getProductId()).eq(Attr::getName, attrDTO.getName()));
        return !ObjectUtils.isEmpty(attrExist);
    }
}
