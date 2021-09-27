package com.roncoo.eshop.cache.ha.degrade;

/**
 * @author 孙许
 * @version 1.0
 * @date 2021/9/27 00:38
 */
public class IsDegrade {

    private static boolean degrade = false;

    public static boolean isDegrade() {
        return degrade;
    }

    public static void setDegrade(boolean degrade) {
        IsDegrade.degrade = degrade;
    }
}
