package per.ccm.ygmall.common.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

public class BaseHandler {

    private static Logger logger;

    protected Logger logger() {
        if (ObjectUtils.isEmpty(logger)) {
            logger = LoggerFactory.getLogger(this.getClass());
        }
        return logger;
    }
}
