package per.ccm.ygmall.auth.feign;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import per.ccm.ygmall.api.auth.bo.AuthAccountBO;
import per.ccm.ygmall.api.auth.feign.AuthAccountFeign;
import per.ccm.ygmall.auth.dto.AuthAccountDTO;
import per.ccm.ygmall.auth.service.AuthAccountService;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.database.util.ConvertUtils;

@Hidden
@RestController
public class AuthAccountFeignController implements AuthAccountFeign {

    @Autowired
    private AuthAccountService authAccountService;

    @Override
    public ResponseEntity<Void> save(AuthAccountBO authAccountBO) throws Exception {
        AuthAccountDTO authAccountDTO = ConvertUtils.boToDTO(authAccountBO, AuthAccountDTO.class);
        authAccountService.save(authAccountDTO);
        return ResponseEntity.success();
    }

    @Override
    public ResponseEntity<Void> update(AuthAccountBO authAccountBO) throws Exception {
        AuthAccountDTO authAccountDTO = ConvertUtils.boToDTO(authAccountBO, AuthAccountDTO.class);
        authAccountService.update(authAccountDTO);
        return ResponseEntity.success();
    }
}
