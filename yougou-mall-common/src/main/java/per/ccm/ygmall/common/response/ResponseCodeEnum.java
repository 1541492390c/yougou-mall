package per.ccm.ygmall.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
     * 用户未登录,请先登录
     * */
    USER_ERROR_A0001("A0001", "用户未登录,请先登录"),

    /**
     * 用户名或密码错误
     * */
    USER_ERROR_A0002("A0002", "用户名或密码错误"),

    /**
     * 无效token
     * */
    USER_ERROR_A0003("A0003", "无效token"),

    /**
     * 访问权限不足
     * */
    USER_ERROR_A0004("A0004", "访问权限不足"),

    /**
     * 登录类型不匹配
     * */
    USER_ERROR_A0005("A0005", "登录类型不匹配"),

    /**
     * 原密码错误
     * */
    USER_ERROR_A0006("A0006", "原密码错误"),

    /**
     * 用户名已存在
     * */
    USER_ERROR_A0007("A0007", "用户名已存在"),

    /**
     * 机号已存在
     * */
    USER_ERROR_A0008("A0008", "手机号已存在"),

    /**
     * 用户不存在
     * */
    USER_ERROR_A009("A0009", "用户不存在"),

    /**
     * 验证码错误
     * */
    USER_ERROR_A0010("A0010", "验证码错误"),

    /**
     * token已过期,请重新登录
     * */
    USER_ERROR_A0011("A0011", "token已过期,请重新登录"),

    /**
     * 用户反馈类型已存在
     * */
    USER_ERROR_A1001("A1001", "用户反馈类型已存在"),

    /**
     * 最多添加5个收货地址
     * */
    USER_ERROR_A2001("A2001", "最多添加5个收货地址"),

    /**
     * 分类已存在
     * */
    PRODUCT_ERROR_B0001("B0001", "分类已存在"),

    /**
     * 商品SPU已存在
     * */
    PRODUCT_ERROR_B1001("B1001", "商品SPU已存在"),

    /**
     * 商品属性已存在
     * */
    PRODUCT_ERROR_B2001("B2001", "商品属性已存在"),

    /**
     * 一个商品最多拥有5个属性
     * */
    PRODUCT_ERROR_B2002("B2002", "一个商品最多拥有5个属性"),

    /**
     * 商品属性值存在重复
     * */
    PRODUCT_ERROR_B3001("B3001", "商品属性值存在重复"),

    /**
     * 商品属性值已存在
     * */
    PRODUCT_ERROR_B3002("B3002", "商品属性值已存在"),

    /**
     * 商品属性最多拥有10个属性值
     * */
    PRODUCT_ERROR_B3003("B3003", "商品属性最多拥有10个属性值"),

    /**
     * 商品sku规格已存在
     * */
    PRODUCT_ERROR_B4001("B4001", "商品sku规格已存在"),

    /**
     * 商品sku库存不足
     * */
    PRODUCT_ERROR_B4002("B4002", "商品sku库存不足"),

    /**
     * 用户已收藏该商品
     * */
    PRODUCT_ERROR_B5001("B5001", "用户已收藏该商品"),

    /**
     * 订单只能对应一个订单收货地址
     * */
    ORDER_ERROR_C0001("B0001", "订单只能对应一个订单收货地址"),;

    private final String value;

    private final String message;
}
