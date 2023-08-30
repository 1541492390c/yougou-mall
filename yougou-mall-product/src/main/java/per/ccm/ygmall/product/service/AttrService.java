package per.ccm.ygmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import per.ccm.ygmall.product.dto.AttrDTO;
import per.ccm.ygmall.product.entity.Attr;
import per.ccm.ygmall.product.vo.AttrVO;

import java.util.List;

public interface AttrService extends IService<Attr> {
    /**
     * 批量保存
     *
     * @param attrList 属性传输数据列表
     * */
    void batchSave(List<AttrDTO> attrList) throws Exception;

    /**
     * 根据spuID获取商品属性列表
     *
     * @param productId 商品ID
     * @return 商品属性列表
     * */
    List<AttrVO> getAttrListByProductId(Long productId) throws Exception;

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
