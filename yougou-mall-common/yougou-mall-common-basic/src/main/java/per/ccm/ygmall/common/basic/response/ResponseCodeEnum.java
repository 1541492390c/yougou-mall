package per.ccm.ygmall.common.basic.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

@Getter
@AllArgsConstructor
public enum ResponseCodeEnum {

    /**
     * 操作成功
     * */
    OK("00000", "操作成功"),

    /**
     * 服务器内部错误
     * */
    SERVER_ERROR_00001("00001", "服务器内部错误"),

    /**
     * 传输参数错误
     * */
    SERVER_ERROR_00002("00002", "传输参数错误"),

    /**
     * 第三方接口错误
     * */
    SERVER_ERROR_00003("00003", "第三方接口错误"),

    /**
     * 用户未登录,请先登录
     * */
    AUTH_ERROR_A0001("A0001", "用户未登录,请先登录"),

    /**
     * 用户名或密码错误
     * */
    AUTH_ERROR_A0002("A0002", "用户名或密码错误"),

    /**
     * 无效token
     * */
    AUTH_ERROR_A0003("A0003", "无效token"),

    /**
     * 访问权限不足
     * */
    AUTH_ERROR_A0004("A0004", "访问权限不足"),

    /**
     * 登录类型不匹配
     * */
    AUTH_ERROR_A0005("A0005", "登录类型不匹配"),

    /**
     * 原密码错误
     * */
    AUTH_ERROR_A0006("A0006", "原密码错误"),

    /**
     * 用户名已存在
     * */
    AUTH_ERROR_A0007("A0007", "用户名已存在"),

    /**
     * 手机号已绑定
     * */
    AUTH_ERROR_A0008("A0008", "手机号已绑定"),

    /**
     * 用户不存在
     * */
    AUTH_ERROR_A009("A0009", "用户不存在"),

    /**
     * 验证码错误
     * */
    AUTH_ERROR_A0010("A0010", "验证码错误"),

    /**
     * token已过期,请重新登录
     * */
    AUTH_ERROR_A0011("A0011", "token已过期,请重新登录"),

    /**
     * 非绑定的手机号
     * */
    AUTH_ERROR_A0012("A0012", "非绑定的手机号"),

    /**
     * 用户不存在
     * */
    USER_ERROR_B0001("B0001", "用户不存在"),

    /**
     * 同一个反馈最多拥有6张图片
     * */
    USER_ERROR_B1001("B1001", "同一个反馈最多拥有6张图片"),

    /**
     * 最多添加6个收货地址
     * */
    USER_ERROR_B2001("B2001", "最多添加6个收货地址"),

    /**
     * 该订单已经评价
     * */
    USER_ERROR_B3001("B3001", "该订单已经评价"),

    /**
     * 分类已存在
     * */
    PRODUCT_ERROR_C0001("C0001", "分类已存在"),

    /**
     * 当前分类节点不存在
     * */
    PRODUCT_ERROR_C0002("C0002", "当前分类节点不存在"),

    /**
     * 商品SPU已存在
     * */
    PRODUCT_ERROR_C1001("C1001", "商品SPU已存在"),

    /**
     * 商品属性已存在
     * */
    PRODUCT_ERROR_C2001("C2001", "商品属性已存在"),

    /**
     * 一个商品最多拥有5个属性
     * */
    PRODUCT_ERROR_C2002("C2002", "一个商品最多拥有5个属性"),

    /**
     * 商品属性值存在重复
     * */
    PRODUCT_ERROR_C3001("C3001", "商品属性值存在重复"),

    /**
     * 商品属性值已存在
     * */
    PRODUCT_ERROR_C3002("C3002", "商品属性值已存在"),

    /**
     * 商品属性最多拥有10个属性值
     * */
    PRODUCT_ERROR_C3003("C3003", "商品属性最多拥有10个属性值"),

    /**
     * 商品sku规格已存在
     * */
    PRODUCT_ERROR_C4001("C4001", "商品sku规格已存在"),

    /**
     * 商品sku库存不足
     * */
    PRODUCT_ERROR_C4002("C4002", "商品sku库存不足"),

    /**
     * 用户已收藏该商品
     * */
    PRODUCT_ERROR_C5001("C5001", "用户已收藏该商品"),

    /**
     * 订单只能对应一个订单收货地址
     * */
    ORDER_ERROR_D0001("D0001", "订单只能对应一个订单收货地址"),

    /**
     * 订单提交失败,存在未支付订单
     * */
    ORDER_ERROR_D0002("D0002", "订单提交失败,存在未支付订单"),

    /**
     * 已领取该优惠券
     * */
    PAYMENT_ERROR_E0001("E0001", "已领取该优惠券"),

    /**
     * 优惠券已领取完
     * */
    PAYMENT_ERROR_E0002("E0001", "优惠券已领取完"),

    /**
     * 优惠券已过期
     * */
    PAYMENT_ERROR_E0003("E0003", "优惠券已过期"),

    /**
     * 同一个页面只能拥有5个轮播图
     * */
    PLATFORM_ERROR_F0001("F0001,", "同一个页面只能拥有5个轮播图"),

    /**
     * 用户反馈类型已存在
     * */
    PLATFORM_ERROR_F1001("F1001", "用户反馈类型已存在")
    ;

    private final String value;

    private final String message;

    public static ResponseCodeEnum getValueOf(String value) {
        for (ResponseCodeEnum responseCodeEnum : ResponseCodeEnum.values()) {
            if (ObjectUtils.nullSafeEquals(responseCodeEnum.getValue(), value)) {
                return responseCodeEnum;
            }
        }
        throw new RuntimeException("ResourceTypeEnum is error --> value is not exist");
    }
}
