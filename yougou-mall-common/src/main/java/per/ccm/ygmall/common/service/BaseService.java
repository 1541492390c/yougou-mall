package per.ccm.ygmall.common.service;

import io.seata.spring.annotation.GlobalTransactional;
import per.ccm.ygmall.common.handler.BaseHandler;

@GlobalTransactional(rollbackFor = Exception.class)
public class BaseService extends BaseHandler {
}
