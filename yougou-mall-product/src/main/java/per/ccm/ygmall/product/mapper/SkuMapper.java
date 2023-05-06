package per.ccm.ygmall.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import per.ccm.ygmall.product.entity.Sku;
import per.ccm.ygmall.product.vo.SkuVO;

import java.util.List;

@Repository
public interface SkuMapper extends BaseMapper<Sku> {
    List<SkuVO> selectSkuList(Long productId);
}
