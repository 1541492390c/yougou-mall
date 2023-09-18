package per.ccm.ygmall.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import per.ccm.ygmall.common.basic.vo.PageVO;
import per.ccm.ygmall.product.dto.BrandDTO;
import per.ccm.ygmall.product.entity.Brand;
import per.ccm.ygmall.product.vo.BrandVO;

import java.util.List;

public interface BrandService extends IService<Brand> {
    /**
     * 保存品牌信息
     *
     * @param brandDTO 品牌传输数据
     * */
    void save(BrandDTO brandDTO) throws Exception;

    /**
     * 获取品牌分页信息
     *
     * @param page 分页信息
     * @return 品牌分页信息
     */
    PageVO<BrandVO> getBrandPages(Page<Brand> page) throws Exception;

    /**
     * 获取品牌分页信息
     *
     * @param categoryNode 分类节点
     * @param name 品牌名称
     * @param page 分页信息
     * @return 品牌分页信息
     */
    PageVO<BrandVO> getBrandPages(String categoryNode, String name, Page<Brand> page) throws Exception;

    /**
     * 获取品牌列表
     *
     * @param categoryNode 分类节点
     * @return 品牌列表
     */
    List<BrandVO> getBrandList(String categoryNode) throws Exception;

    /**
     * 更新品牌信息
     *
     * @param brandDTO 品牌传输数据
     */
    void update(BrandDTO brandDTO) throws Exception;
}
