package com.roncoo.eshop.cache.ha.hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @author 孙许
 * @version 1.0
 * @date 2021/9/24 23:18
 */
public class UpdateProductInfoCommand extends HystrixCommand<Boolean> {

    private Long productId;

    public UpdateProductInfoCommand(Long productId) {
        super(HystrixCommandGroupKey.Factory.asKey("UpdateProductInfoGroup"));
        this.productId = productId;
    }

    @Override
    protected Boolean run() {
        // 执行一次商品信息的更新
//        GetProductInfoCommand.flushCache(productId);
        return true;
    }
}
