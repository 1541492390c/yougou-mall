package per.ccm.ygmall.product.service;

import per.ccm.ygmall.product.dto.AttrValueDTO;
import per.ccm.ygmall.product.vo.AttrValueVO;

import java.util.List;

public interface AttrValueService {
    /**
     * 批量保存属性值
     *
     * @param attrDTOList 属性值传输数据列表
     * */
    void batchSave(List<AttrValueDTO> attrDTOList) throws Exception;

    /**
     * 查询属性值ID列表中的属性值
     *
     * @param attrIdValueList 商品属性值ID
     * @return 商品属性值信息列表
     * */
    List<AttrValueVO> getAttrValueListByAttrValueIdList(List<Long> attrIdValueList) throws Exception;

    /**
     *根据商品属性值ID批量删除商品属性值
     *
     * @param attrValueIdList 商品属性值ID列表
     * */
    void batchDelete(List<Long> attrValueIdList) throws Exception;

    /**
     * 根据商品属性ID批量删除商品属性值
     *
     * @param attrIdList 商品属性ID列表
     * */
    void batchDeleteByAttrIdList(List<Long> attrIdList) throws Exception;
}
