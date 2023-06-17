package per.ccm.ygmall.common.response;

import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.common.exception.ServerException;

public enum ResponseCode {

    /**
     * 操作成功
     * */
    OK("00000", "操作成功"),

    /**
     * 服务器内部错误
     * */
    SERVER_ERROR_000001("00001", "服务器内部错误"),

    /**
     * 传输参数错误
     * */
    PARAM_ERROR_00002("00002", "传输参数错误"),

    /**
     * 用户未登录或验证失败
     * */
    USER_ERROR_A00001("A00001", "用户未登录或验证失败"),

    /**
     * 用户名或密码错误
     * */
    USER_ERROR_A00002("A00002", "用户名或密码错误"),

    /**
     * 无效token
     * */
    USER_ERROR_A00003("A00003", "无效token"),

    /**
     * 访问权限不足
     * */
    USER_ERROR_A00004("A00004", "访问权限不足"),

    /**
     * 登录类型不匹配
     * */
    USER_ERROR_A00005("A00005", "登录类型不匹配"),

    /**
     * 原密码错误
     * */
    USER_ERROR_A00006("A00006", "原密码错误"),

    /**
     * 用户名已存在
     * */
    USER_ERROR_A00007("A00007", "用户名已存在"),

    /**
     * 机号已存在
     * */
    USER_ERROR_A00008("A00008", "手机号已存在"),

    /**
     * 用户不存在
     * */
    USER_ERROR_A0009("A00009", "用户不存在"),

    /**
     * 验证码错误
     * */
    USER_ERROR_A00010("A00010", "验证码错误"),

    /**
     * 用户反馈类型已存在
     * */
    USER_ERROR_A10001("A10001", "用户反馈类型已存在"),

    /**
     * 分类已存在
     * */
    PRODUCT_ERROR_B00001("B00001", "分类已存在"),

    /**
     * 商品SPU已存在
     * */
    PRODUCT_ERROR_B10001("B10001", "商品SPU已存在"),

    /**
     * 商品属性已存在
     * */
    PRODUCT_ERROR_B2001("B20001", "商品属性已存在"),


    /**
     * 一个商品最多拥有5个属性
     * */
    PRODUCT_ERROR_B20002("B20002", "一个商品最多拥有5个属性"),

    /**
     * 商品属性值存在重复
     * */
    PRODUCT_ERROR_B30001("B30001", "商品属性值存在重复"),

    /**
     * 商品属性值已存在
     * */
    PRODUCT_ERROR_B30002("B30002", "商品属性值已存在"),

    /**
     * 商品属性最多拥有10个属性值
     * */
    PRODUCT_ERROR_B30003("B30003", "商品属性最多拥有10个属性值"),

    /**
     * 商品sku规格已存在
     * */
    PRODUCT_ERROR_B40001("B40001", "商品sku规格已存在");

    private final String value;
    private final String message;

    ResponseCode(String value, String message) {
        this.value = value;
        this.message = message;
    }

    public String value() {
        return value;
    }

    public String message() {
        return message;
    }

    public static ResponseCode responseCodeOf(String value) {
        for (ResponseCode responseCode : ResponseCode.values()) {
            if (ObjectUtils.nullSafeEquals(responseCode.value(), value)) {
                return responseCode;
            }
        }
        throw new ServerException("响应码不存在");
    }
}
