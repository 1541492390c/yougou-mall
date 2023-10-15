package per.ccm.ygmall.common.cache.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CacheEnum {

    /**
     * 认证token
     * */
    ACCESS_TOKEN(CacheNames.ACCESS_TOKEN_NAME, 14 * 24 * 60 * 60L),

    /**
     * 用户信息
     * */
    USERINFO(CacheNames.USERINFO_CACHE_NAME, 7 * 24 * 60 * 60L),

    /**
     * 商品分类
     * */
    CATEGORY(CacheNames.PRODUCT_CATEGORY_CACHE_NAME, 7 * 24 * 60 * 60L),

    /**
     * 商品
     * */
    PRODUCT(CacheNames.PRODUCT_CACHE_NAME, 7 * 24 * 60 * 60L),

    /**
     * 属性
     * */
    ATTR(CacheNames.PRODUCT_ATTR_CACHE_NAME, 7 * 24 * 60 * 60L),

    /**
     * 轮播图
     * */
    BANNER(CacheNames.BANNER_CACHE_NAME, 7 * 24 * 60 * 60L),

    /**
     * 验证码
     * */
    EXTRA_VALIDATE_CODE(CacheNames.EXTRA_VALIDATE_CODE_NAME, 5 * 60L);

    private final String value;

    private final Long expired;
}
