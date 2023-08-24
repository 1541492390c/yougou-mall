package per.ccm.ygmall.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import per.ccm.ygmall.common.vo.PageVO;
import per.ccm.ygmall.product.entity.Brand;
import per.ccm.ygmall.product.vo.BrandVO;

import java.util.List;

public interface BrandService extends IService<Brand> {
    /**
     * 获取品牌分页信息
     *
     * @param page 分页信息
     * @return 品牌分页信息
     * */
    PageVO<BrandVO> getBrandPages(Page<Brand> page) throws Exception;

    /**
     * 获取品牌列表
     *
     * @param categoryNode 分类节点
     * @return 品牌列表
     * */
    List<BrandVO> getBrandList(String categoryNode) throws Exception;
}
