package per.ccm.ygmall.user.controller.common;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.security.util.SecurityContextUtils;
import per.ccm.ygmall.user.dto.AddrDTO;
import per.ccm.ygmall.user.service.AddrService;
import per.ccm.ygmall.user.vo.AddrVO;

import java.util.List;

@RestController
@RequestMapping("/user/addr")
@Tag(name = "收获地址接口", description = "收获地址接口")
public class AddrController {

    @Autowired
    private AddrService addrService;

    @PostMapping("/save")
    @Operation(summary = "保存收货地址", description = "保存收货地址")
    public ResponseEntity<Void> save(@RequestBody AddrDTO addrDTO) throws Exception {
        Long userId = SecurityContextUtils.getUserId();

        addrDTO.setUserId(userId);
        addrService.save(addrDTO);
        return ResponseEntity.success();
    }

    @GetMapping("/list")
    @Operation(summary = "获取收获地址列表", description = "获取收获地址列表")
    public ResponseEntity<List<AddrVO>> getAddrList() throws Exception {
        Long userId = SecurityContextUtils.getUserId();
        List<AddrVO> addrList = addrService.getAddrList(userId);
        return ResponseEntity.success(addrList);
    }

    @PutMapping("/update")
    @Operation(summary = "更新收货地址", description = "更新收货地址")
    public ResponseEntity<Void> update(@RequestBody AddrDTO addrDTO) throws Exception {
        Long userId = SecurityContextUtils.getUserId();

        addrDTO.setUserId(userId);
        addrService.update(addrDTO);
        return ResponseEntity.success();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "根据主键ID删除收货地址信息", description = "根据主键ID删除收货地址信息")
    @Parameter(name = "addr_id", description = "主键ID")
    public ResponseEntity<Void> delete(@RequestParam("addr_id") Long addrId) throws Exception {
        addrService.delete(addrId);
        return ResponseEntity.success();
    }
}
