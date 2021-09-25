package com.roncoo.eshop.cache.ha.cache.local;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 孙许
 * @version 1.0
 * @date 2021/9/25 14:52
 * 本地过期品牌缓存
 */
public class BrandCache {

    public static Map<Long, String> brandMap = new HashMap<>();

    static {
        brandMap.put(1L, "iphone");
    }

    public static String getBrandName(Long cityId) {
        return brandMap.get(cityId);
    }
}
