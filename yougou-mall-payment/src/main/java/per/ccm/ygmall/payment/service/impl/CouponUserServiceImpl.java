package per.ccm.ygmall.payment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.common.basic.exception.YougouException;
import per.ccm.ygmall.common.basic.response.ResponseCodeEnum;
import per.ccm.ygmall.common.basic.util.ConvertUtils;
import per.ccm.ygmall.feign.payment.bo.CouponBO;
import per.ccm.ygmall.feign.payment.bo.CouponUserBO;
import per.ccm.ygmall.payment.dto.QueryAvailableCouponDTO;
import per.ccm.ygmall.payment.entity.CouponUser;
import per.ccm.ygmall.payment.enums.CouponUserStateEnum;
import per.ccm.ygmall.payment.mapper.CouponUserMapper;
import per.ccm.ygmall.payment.service.CouponUserService;
import per.ccm.ygmall.payment.vo.CouponUserVO;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CouponUserServiceImpl extends ServiceImpl<CouponUserMapper, CouponUser> implements CouponUserService {

    @Autowired
    private CouponUserMapper couponUserMapper;

    @Override
    public List<CouponUserVO> getCouponUserListByUserId(Long userId) {
        return couponUserMapper.selectCouponUserListByUserId(userId);
    }

    @Override
    public Boolean isReceive(Long couponId, Long userId) {
        LambdaQueryWrapper<CouponUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CouponUser::getCouponId, couponId).eq(CouponUser::getUserId, userId);
        return couponUserMapper.exists(queryWrapper);
    }

    @Override
    public List<CouponUserVO> getAvailableCoupon(QueryAvailableCouponDTO queryAvailableCouponDTO) {
        // 查询用户所有优惠券
        List<CouponUserVO> couponUserList = couponUserMapper.selectCouponUserListByUserId(queryAvailableCouponDTO.getUserId());

        // 过滤出状态为待使用和通用优惠券
        couponUserList = couponUserList
                .stream()
                .filter(item -> ObjectUtils.nullSafeEquals(item.getState(), CouponUserStateEnum.WAIT_USE.getValue())
                        && queryAvailableCouponDTO.getTotalAmount().compareTo(item.getCoupon().getUsedAmount()) >= 0)
                .collect(Collectors.toList());

        // 将所有分类节点转为列表
        List<String> currentNodeList = new ArrayList<>();
        for (String node : queryAvailableCouponDTO.getCategoryNodeList()) {
            List<String> nodeList = Arrays.asList(node.split("-"));
            currentNodeList.addAll(nodeList);
        }

        // 过滤出对应分类的优惠券
        couponUserList = couponUserList
                .stream()
                .filter(item -> {
                    // 通用优惠券
                    if (ObjectUtils.nullSafeEquals(item.getCoupon().getCategoryNode(), "0")) {
                        return Boolean.TRUE;
                    }
                    List<String> nodeList = Arrays.asList(item.getCoupon().getCategoryNode().split("-"));
                    return new HashSet<>(currentNodeList).containsAll(nodeList);
                }).collect(Collectors.toList());
        return couponUserList;
    }

    @Override
    public CouponUserBO getCouponUserBOById(Long couponUserId) {
        CouponUserVO couponUserVO = couponUserMapper.selectCouponUserListByCouponUserId(couponUserId);

        // 优惠券已过期
        if (couponUserVO.getExpiredTime().getTime() <= new Date().getTime()) {
            throw new YougouException(ResponseCodeEnum.PAYMENT_ERROR_E0003);
        }

        // 转换为用户优惠券内部传输数据
        CouponUserBO couponUserBO = ConvertUtils.convertProperties(couponUserVO, CouponUserBO.class);
        // 转换为优惠券内部传输数据
        CouponBO couponBO = ConvertUtils.convertProperties(couponUserVO.getCoupon(), CouponBO.class);
        // 设置优惠券信息
        couponUserBO.setCouponBO(couponBO);
        return couponUserBO;
    }
}
