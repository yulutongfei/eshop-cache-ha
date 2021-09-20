package com.roncoo.eshop.cache.ha.cache.local;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 孙许
 * @version 1.0
 * @date 2021/9/20 15:43
 */
public class LocationCache {

    public static Map<Long, String> cityMap = new HashMap<>();

    static {
        cityMap.put(1L, "北京");
    }

    public static String getCityName(Long cityId) {
        return cityMap.get(cityId);
    }
}
