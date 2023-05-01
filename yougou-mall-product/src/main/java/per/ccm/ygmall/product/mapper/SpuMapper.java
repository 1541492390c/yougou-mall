package per.ccm.ygmall.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import per.ccm.ygmall.product.entity.Spu;
import per.ccm.ygmall.product.vo.SpuVO;

import java.util.List;

@Repository
public interface SpuMapper extends BaseMapper<Spu> {
    List<SpuVO> selectRecommendedSpuList();

    IPage<SpuVO> selectSpuPages(@Param("categories") String categories, Page<Spu> page);
}
