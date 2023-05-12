package per.ccm.ygmall.platform.controller.common;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.platform.service.BannerService;
import per.ccm.ygmall.platform.vo.BannerVO;

import java.util.List;

@RestController
@RequestMapping("/banner")
@Tag(name = "轮播图(通用)", description = "轮播图(通用)")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @GetMapping("/list")
    @Operation(summary = "获取轮播图列表", description = "获取轮播图列表")
    @Parameters({
            @Parameter(name = "type", description = "轮播图类型", required = true),
            @Parameter(name = "page", description = "所属页面", required = true)})
    public ResponseEntity<List<BannerVO>> getBannerList(Integer type, String page) throws Exception {
        List<BannerVO> bannerList = bannerService.getBannerList(type, page);
        return ResponseEntity.success(bannerList);
    }
}
