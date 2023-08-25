package per.ccm.ygmall.product.controller.common;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.ccm.ygmall.common.basic.response.ResponseEntity;
import per.ccm.ygmall.product.service.SecKillService;
import per.ccm.ygmall.product.vo.SecKillVO;

import java.util.List;

@RestController
@RequestMapping("/product/sec_kill")
@Tag(name = "秒杀活动场次接口", description = "秒杀活动场次接口")
public class SecKillController {

    @Autowired
    private SecKillService secKillService;

    @GetMapping("/list")
    public ResponseEntity<List<SecKillVO>> getSecKillList() throws Exception {
        List<SecKillVO> secKillList = secKillService.getSecKillList();
        return ResponseEntity.success(secKillList);
    }
}
