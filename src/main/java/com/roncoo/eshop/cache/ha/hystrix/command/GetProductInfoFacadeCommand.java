package com.roncoo.eshop.cache.ha.hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.roncoo.eshop.cache.ha.degrade.IsDegrade;
import com.roncoo.eshop.cache.ha.model.ProductInfo;

/**
 * @author 孙许
 * @version 1.0
 * @date 2021/9/27 00:34
 */
public class GetProductInfoFacadeCommand extends HystrixCommand<ProductInfo> {

    private Long productId;

    public GetProductInfoFacadeCommand(Long productId) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ProductInfoService"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("GetProductInfoFacadeCommand"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                        .withExecutionIsolationSemaphoreMaxConcurrentRequests(15)));
        this.productId = productId;
    }

    @Override
    protected ProductInfo run() {
        if (!IsDegrade.isDegrade()) {
            return new GetProductInfoCommand(productId).execute();
        } else {
            return new GetProductInfoFromMySQLCommand(productId).execute();
        }
    }

    @Override
    protected ProductInfo getFallback() {
        return new ProductInfo();
    }
}
