package per.ccm.ygmall.common.service;

import org.springframework.transaction.annotation.Transactional;
import per.ccm.ygmall.common.handler.BaseHandler;

@Transactional(rollbackFor = Exception.class)
public class BaseService extends BaseHandler {
}
