package per.ccm.ygmall.platform.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.common.basic.response.ResponseEntity;
import per.ccm.ygmall.common.basic.vo.PageVO;
import per.ccm.ygmall.platform.dto.BannerDTO;
import per.ccm.ygmall.platform.entity.Banner;
import per.ccm.ygmall.platform.service.BannerService;
import per.ccm.ygmall.platform.vo.BannerVO;

@RestController("adminBannerController")
@RequestMapping("/admin/platform/banner")
@PreAuthorize("hasAnyRole(@roleConfig.SUPER_ADMIN, @roleConfig.COMMON_ADMIN)")
@Tag(name = "轮播图接口(管理员)", description = "轮播图接口(管理员)")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @PostMapping("/save")
    @Operation(summary = "保存轮播图信息", description = "保存轮播图信息")
    public ResponseEntity<Void> save(@RequestBody BannerDTO bannerDTO) throws Exception {
        bannerService.save(bannerDTO);
        return ResponseEntity.success();
    }

    @GetMapping("/pages")
    @Operation(summary = "分页获取轮播图信息", description = "分页获取轮播图信息")
    @Parameters({
            @Parameter(name = "pageNum", description = "当前页"),
            @Parameter(name = "pageSize", description = "页数"),
            @Parameter(name = "type", description = "类型")
    })
    public ResponseEntity<PageVO<BannerVO>> getBannerPages(
            @RequestParam(value = "page_num", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "type", required = false) Integer type) throws Exception {
        Page<Banner> page = new Page<>(pageNum, pageSize);
        PageVO<BannerVO> pageVO = bannerService.getBannerPages(type, page);
        return ResponseEntity.success(pageVO);
    }
}
