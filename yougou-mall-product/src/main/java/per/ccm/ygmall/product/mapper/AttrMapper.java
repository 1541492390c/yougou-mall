package per.ccm.ygmall.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import per.ccm.ygmall.product.entity.Attr;
import per.ccm.ygmall.product.vo.AttrVO;

import java.util.List;

@Repository
public interface AttrMapper extends BaseMapper<Attr> {
    List<AttrVO> selectAttrList(Long productId);
}
