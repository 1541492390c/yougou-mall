package per.ccm.ygmall.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import per.ccm.ygmall.product.entity.AttrValue;
import per.ccm.ygmall.product.vo.AttrValueVO;

import java.util.List;

@Repository
public interface AttrValueMapper extends BaseMapper<AttrValue> {
    List<AttrValueVO> selectAttrValueListByAttrId(Long attrId);
}
