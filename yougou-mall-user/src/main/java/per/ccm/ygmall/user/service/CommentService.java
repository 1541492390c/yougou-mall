package per.ccm.ygmall.user.service;

import per.ccm.ygmall.user.vo.RateStatisticsVO;

public interface CommentService {
    /**
     * 获取商品评分统计信息
     *
     * @param productId 商品ID
     * @return 商品评分统计信息
     * */
    RateStatisticsVO getRateStatistics(Long productId) throws Exception;
}
