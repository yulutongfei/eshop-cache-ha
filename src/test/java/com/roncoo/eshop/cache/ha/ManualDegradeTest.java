package com.roncoo.eshop.cache.ha;

import com.roncoo.eshop.cache.ha.degrade.IsDegrade;
import com.roncoo.eshop.cache.ha.hystrix.command.GetProductInfoFacadeCommand;

/**
 * @author 孙许
 * @version 1.0
 * @date 2021/9/27 00:43
 */
public class ManualDegradeTest {

    public static void main(String[] args) {
        GetProductInfoFacadeCommand getProductInfoFacadeCommand = new GetProductInfoFacadeCommand(1L);
        System.out.println(getProductInfoFacadeCommand.execute());

        IsDegrade.setDegrade(true);
        System.out.println(getProductInfoFacadeCommand.execute());
    }
}
