package per.ccm.ygmall.product.service;

import per.ccm.ygmall.product.dto.AttrDTO;
import per.ccm.ygmall.product.vo.AttrVO;

import java.util.List;

public interface AttrService {
    /**
     * 保存商品属性信息
     *
     * @param attrDTO 商品属性传输数据
     * */
    void save(AttrDTO attrDTO) throws Exception;

    /**
     * 根据spuID获取商品属性列表
     *
     * @param spuId spuID
     * @return 商品属性列表
     * */
    List<AttrVO> getAttrListBySpuId(Long spuId) throws Exception;

    /**
     * 更新商品属性信息
     *
     * @param attrDTO 商品属性传输数据
     * */
    void update(AttrDTO attrDTO) throws Exception;

    /**
     * 根据商品属性ID批量删除商品属性
     *
     * @param attrIdList 商品属性ID列表
     * */
    void batchDelete(List<Long> attrIdList) throws Exception;
}
