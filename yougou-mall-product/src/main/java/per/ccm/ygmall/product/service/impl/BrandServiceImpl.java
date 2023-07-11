package per.ccm.ygmall.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import per.ccm.ygmall.product.entity.Brand;
import per.ccm.ygmall.product.mapper.BrandMapper;
import per.ccm.ygmall.product.service.BrandService;

@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {
}
